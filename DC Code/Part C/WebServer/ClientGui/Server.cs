using IronPython.Hosting;
using Microsoft.Scripting.Hosting;
using Newtonsoft.Json;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Security.Cryptography;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace ClientGui
{
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Single, UseSynchronizationContext = false)]
    public class Server : IServer
    {
        static List<Job> jobs = new List<Job>();
        static List<string> results = new List<string>();
        public static bool run = true;

        public Server() { }

        public void AddJob(Job j)
        {
            jobs.Add(j);
        }

        public void AddResult(string r)
        {
            results.Add(r);
        }

        public List<string> GetResults()
        {
            return results;
        }

        public Job GetNextJob()
        {
            //Lock.Locked = true;              
            Job temp =  jobs.FirstOrDefault();
            jobs.Remove(temp);
            return temp;
            
        }

        public void UpdateJob(int id)
        {
            Job remove = null;
            foreach (Job j in jobs)
            {
                if(j.Id == id)
                {
                    remove = j;
                }
            }
            if(remove != null)
            {
                jobs.Remove(remove);
            }
        }

        public string CompleteJob(string pythonCode, byte[] hash) // Runs the python code to do the job
        {
            SHA256 sha256 = SHA256.Create();
            if (hash.SequenceEqual(sha256.ComputeHash(Encoding.UTF8.GetBytes(pythonCode))))
            {
                Debug.WriteLine("So I'm guessing we never get here?");
                string code = Decode(pythonCode);
                ScriptEngine engine = Python.CreateEngine();
                ScriptScope scope = engine.CreateScope();
                engine.Execute(code, scope);

                dynamic mainFunction = scope.GetVariable("main");
                var result = mainFunction();

                return code + "\nGives result:\n" + result.ToString();
            }
            else 
            {
                Debug.WriteLine("But instead here");
                return null; 
            }
        }


        public string Decode(string data) // Base 64 Decoding
        {
            if (string.IsNullOrEmpty(data))
            {
                return data;
            }

            byte[] encodedBytes = Convert.FromBase64String(data);

            return Encoding.UTF8.GetString(encodedBytes);
        }
    }
}

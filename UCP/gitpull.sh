#!user/bin/bash

cd /mnt/home/18439731
rm ~/.ssh
mkdir ~/.ssh
cp id_ed25519 ~/.ssh
cp id_ed25519.pub ~/.ssh
chmod 700 ~/.ssh/id_ed25519
chmod 700 ~/.ssh/id_ed25519.pub
ssh-add
cd Documents/
git pull
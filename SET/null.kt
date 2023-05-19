fun main()
{
    val result = mutableListOf<String>()
    var h = "hello"
    val w = returnNull()

    result += h

    print(result)

    result += w

    print(result)

}

fun returnNull(): String?
{
    return null
}

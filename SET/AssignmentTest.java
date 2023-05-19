public class AssignmentTest 
{
    @Test 
    public void testParse() throws Exception 
    {
        MediaType mediaType = parse("text/plain;boundary=foo;charset=utf-8");
        assertThat(mediaType.type()).isEqualTo("text");
        assertThat(mediaType.subtype()).isEqualTo("plain");
        assertThat(mediaType.charset().name()).isEqualTo("UTF-8");
        assertThat(mediaType.toString()).isEqualTo("text/plain;boundary=foo;charset=utf-8");
        assertThat(parse("text/plain;boundary=foo;charset=utf-8")).isEqualTo(mediaType);
        assertThat(parse("text/plain;boundary=foo;charset=utf-8").hashCode()).isEqualTo((long) mediaType.hashCode());
    }


    private MediaType parse(String string) {
        return useGet
            ? MediaType.get(string)
            : MediaType.parse(string);
      }

    








    @Test 
    public void addParsing() 
    {
        Headers headers = new Headers.Builder()
            .add("foo: bar")
            .add(" foo: baz") // Name leading whitespace is trimmed.
            .add("foo : bak") // Name trailing whitespace is trimmed.
            .add("\tkey\t:\tvalue\t") // '\t' also counts as whitespace
            .add("ping:  pong  ") // Value whitespace is trimmed.
            .add("kit:kat") // Space after colon is not required.
            .build();
        assertThat(headers.values("foo")).containsExactly("bar", "baz", "bak");
        assertThat(headers.values("key")).containsExactly("value");
        assertThat(headers.values("ping")).containsExactly("pong");
        assertThat(headers.values("kit")).containsExactly("kat");
    }
    
    
}



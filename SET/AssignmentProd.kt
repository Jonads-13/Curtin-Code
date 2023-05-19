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


fun parse(mediaType: String): MediaType? = mediaType.toMediaTypeOrNull()


fun String.toMediaTypeOrNull(): MediaType? {
	return try {
	toMediaType()
	} catch (_: IllegalArgumentException) {
	null
	}
}


fun String.toMediaType(): MediaType
   {
	    /*Node 1*/
    	val typeSubtype = TYPE_SUBTYPE.matcher(this)

	    /*Node 2*/
    	require(typeSubtype.lookingAt()) { /*Node 3*/ "No subtype found for: \"$this\"" }

    	val type = typeSubtype.group(1).toLowerCase(Locale.US)
    	val subtype = typeSubtype.group(2).toLowerCase(Locale.US)
    	val parameterNamesAndValues = mutableListOf<String>()
    	val parameter = PARAMETER.matcher(this)
    	var s = typeSubtype.end()

	    /*Node 4*/
    	while (s < length)
    	{
        	parameter.region(s, length)
	        /*Node 5*/
        	require(parameter.lookingAt())
        	{
            	/*Node 6*/
            	"Parameter is not formatted correctly: \"${substring(s)}\" for: \"$this\""
        	}

        	val name = parameter.group(1)
        	/*Node 7*/
        	if (name == null) {
            	/*Node 8*/
            	s = parameter.end()
            	continue
        	}

        	val token = parameter.group(2)
        	/*Node 9*/
        	val value = when
        	{
            	/*Node 10*/
            	token == null ->
            	{
                	// Value is "double-quoted". That's valid and our regex group already strips the quotes.
                	parameter.group(3)
            	}
            	/*Node 11*/
            	token.startsWith("'") && token.endsWith("'") && token.length > 2 ->
            	{
                	// If the token is 'single-quoted' it's invalid! But we're lenient and strip the quotes.
                	token.substring(1, token.length - 1)
            	}
            	/*Node 12*/
            	else -> token
        	}

        	/*Node 13*/
        	parameterNamesAndValues += name
        	parameterNamesAndValues += value
        	s = parameter.end()
    	}

  	/*Node 14*/
  	return MediaType(this, type, subtype, parameterNamesAndValues.toTypedArray())
	}












fun values(name: String): List<String>
{
  /*Node 1*/
	var result: MutableList<String>? = null
	/*Node 2*/
	for (i in 0 until size)
	{
  	/*Node 3*/
  	if (name.equals(name(i), ignoreCase = true))
  	{
    	/*Node 4*/
    	if (result == null) result = ArrayList(2) /*Node 5*/
    	/*Node 6*/
    	result.add(value(i))
 }
	}
	/*Node 7*/
	return if (result != null)
	{/*Node 8*/
  		Collections.unmodifiableList(result)
	}
	else
	{/*Node 9*/
  		emptyList()
	}
	/*Node 10 Assumes resolve if then returns that state*/
  }







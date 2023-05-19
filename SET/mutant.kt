   fun String.toMediaType(): MediaType
   {
    	val typeSubtype = TYPE_SUBTYPE.matcher(this)

    	require(typeSubtype.lookingAt()) { "No subtype found for: \"$this\"" }

    	val type = typeSubtype.group(1).toLowerCase(Locale.US)
    	val subtype = typeSubtype.group(2).toLowerCase(Locale.US)
    	val parameterNamesAndValues = mutableListOf<String>()
    	val parameter = PARAMETER.matcher(this)
    	var s = typeSubtype.end()

    	while (s < length)
/* ∆1 while( s > length ) */
    	{
        	parameter.region(s, length)
        	require(parameter.lookingAt())
        	{
            	"Parameter is not formatted correctly: \"${substring(s)}\" for: \"$this\""
        	}

        	val name = parameter.group(1)
        	if (name == null) {
            	s = parameter.end()
            	continue
/* ∆2 break */
        	}

        	val token = parameter.group(2)
        	val value = when
        	{
            	token == null ->
/* ∆3 token != null */
            	{
                	// Value is "double-quoted". That's valid and our regex group already strips the quotes.
                	parameter.group(3)
            	}
        	 
            	token.startsWith("'") && token.endsWith("'") && token.length > 2 ->
            	{
                	// If the token is 'single-quoted' it's invalid! But we're lenient and strip the quotes.
                	token.substring(1, token.length - 1)
            	}
            	else -> token
        	}
/* ∆4 parameterNamesAndValues += value (moved above name) */
        	parameterNamesAndValues += name
        	parameterNamesAndValues += value        	

        	s = parameter.end()
    	}

  	return MediaType(this, type, subtype, parameterNamesAndValues.toTypedArray())
	}



fun values(name: String): List<String>
{
	var result: MutableList<String>? = null
	for (i in 0 until size)
/* ∆1 for (i in 0 until 10) */
	{
  	if (name.equals( name(i), ignoreCase = true ))
/* ∆2 ignoreCase = false or remove it */
  	{
    	if (result == null) result = ArrayList(2)
    	result.add(value(i))
/* ∆3 result.add(value(i+1)) */
  	}
	}
	return if (result != null)
	{
  	Collections.unmodifiableList(result)
	}
	else
	{
/* ∆4 Bomb() */
  	emptyList()
	}
}


  


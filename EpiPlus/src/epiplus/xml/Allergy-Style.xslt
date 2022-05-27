<xsl:stylesheet version ="2.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transfrom">
<xsl:output method ="html" indent="yes"/>

<xsl:template> match ="/">
	<html>>
	<p><b><xsl:value-of select="//name" /></b></p>
	<p><b> Content: </b> <xsl:value-of select= "//patient"/></p>
	</html>
	</xsl:template>
	
</xsl:stylesheet>


 
<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version ="2.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">
<xsl:output method ="html" indent="yes"/>

<xsl:template match ="/">
	<html>
	<p><b><xsl:value-of select="//@id" /></b></p>
	<p><b><xsl:value-of select="//@name" /></b></p>
	<p><b> Content: </b> <xsl:value-of select= "//patients"/></p>
	</html>
</xsl:template>
	
</xsl:stylesheet>


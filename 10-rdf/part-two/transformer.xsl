<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xpath-default-namespace="http://kyjovsm.mff.cuni.cz/client">
    <xsl:output method="text" encoding="UTF-8" indent="no"/>
    <xsl:variable name="prefix">http://kyjovsm.mff.cuni.cz/client</xsl:variable>
   
   <xsl:template match="client">
        @prefix rdf: &lt;http://www.w3.org/1999/02/22-rdf-syntax-ns#&gt; .
        @prefix rdfs: &lt;http://www.w3.org/2000/01/rdf-schema#&gt; .
        @prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .
        @prefix foaf: &lt;http://xmlns.com/foaf/0.1#&gt; .
        @prefix lf: &lt;http://kyjovsm.mff.cuni.cz/nswi145/litigation#&gt; .
        <xsl:variable name="currentURI" select="concat($prefix, ID)"/>
        &lt;<xsl:value-of select="$currentURI"/>&gt; a foaf:Client ;
        rdfs:comment &quot;Client:<xsl:value-of select="id"/>&quot;@en ;
        foaf:firstName &quot;<xsl:value-of select="firstname"/>&quot;^^xsd:string ;
        foaf:firstName &quot;<xsl:value-of select="lastname"/>&quot;^^xsd:string ;
        lf:email &quot;<xsl:value-of select="email"/>&quot; .    
    
   
    </xsl:template>
    <xsl:template match="text()"/>
   
</xsl:stylesheet>
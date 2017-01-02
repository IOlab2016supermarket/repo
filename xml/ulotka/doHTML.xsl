<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" />

    <xsl:template match="/">
        <html xml:lang="pl" lang="pl">
            <head>
                <title>Supermarket</title>
		<link rel="stylesheet" type="text/css" href="html.css"/>
            </head>
            <body>
                    <div class="nagłówek">
                        <h1>Supermarket</h1>
                            <h2>Codziennie najniższe ceny -
                            	przyjdź i sprawdź</h2>
					
                    </div>
                    <xsl:call-template name="map"/>                                                                      
            </body>
        </html>
    </xsl:template>	

    <xsl:template name="map">
	<xsl:if test="count(map/entry/marketing.Promocja/id__produkt[text()!='0'])!=0">
	<div class="Tabela">
        <h3>Produkty obecnie w promocji:   <text class="polecane">POLECANE</text></h3>
			<table>
                <tbody>
					<tr>
                        <th>Nazwa</th>
                        <th>Cena</th>
						<th>Promocja trwa do:</th>
					</tr>
                    <xsl:for-each select="map/entry">
						<xsl:if test="marketing.Promocja/id__produkt[text()!='0']">
						<tr class="Lista">
                            <td class="centerNazwa">
                                    <xsl:value-of select="wspolne.Produkt/nazwa"/>
                            </td>
                            <td class="center">
                                    <xsl:value-of select="wspolne.Produkt/cenaSprzedazy"/>
                            </td>
                            <td class="center">
							    <xsl:choose>
                                    <xsl:when test="marketing.Promocja/id__produkt[text()='0']">
										<b class="brakPromocji">-</b>
                                    </xsl:when>
                                    <xsl:otherwise>
										<b class="dataDoKiedy"><xsl:value-of select="marketing.Promocja/do__kiedy"/></b> 
                                    </xsl:otherwise>                            
								</xsl:choose>                          
                            </td>
                        </tr>
						</xsl:if>
                    </xsl:for-each>					
                </tbody>
			</table>
        </div>
		</xsl:if>
		
		<div class="Tabela">
		<xsl:if test="count(map/entry/marketing.Promocja/id__produkt[text()!='0'])=0">
			<h3>Produkty w ofercie</h3>
		</xsl:if>
		<xsl:if test="count(map/entry/marketing.Promocja/id__produkt[text()!='0'])!=0">
			<h3>Inne produkty w ofercie:</h3>
		</xsl:if>
			<table>
                <tbody>
					<tr>
                        <th>Nazwa</th>
                        <th>Cena</th>
					</tr>
                    <xsl:for-each select="map/entry">
						<xsl:if test="marketing.Promocja/id__produkt[text()='0']">
						<tr class="Lista">
                            <td class="centerNazwa">
                                    <xsl:value-of select="wspolne.Produkt/nazwa"/>
                            </td>
                            <td class="center">
                                    <xsl:value-of select="wspolne.Produkt/cenaSprzedazy"/>
                            </td>
                        </tr>
						</xsl:if>
                    </xsl:for-each>					
                </tbody>
			</table>
        </div>	
    </xsl:template>
    
</xsl:stylesheet>

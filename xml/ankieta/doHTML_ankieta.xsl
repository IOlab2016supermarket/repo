<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" />

    <xsl:template match="/">
        <html xml:lang="pl" lang="pl">
            <head>
                <title>Ankieta-Supermarket</title>
		<link rel="stylesheet" type="text/css" href="html_ankieta.css"/>
            </head>
            <body>
                    <div class="nagłówek">
                        <h1>Ankieta</h1>
                            <h2><xsl:value-of select="marketing.Ankieta/tytul"/></h2>
					
                    </div>
                    <xsl:call-template name="marketing.Ankieta"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script type="text/javascript"><![CDATA[
				
				function zapisz() 
				{
					var odpowiedzi = document.getElementById('tabelka');
					var input = odpowiedzi.getElementsByTagName('input'); //pobieramy wszystkie input z tabeli
					var test = true;
					for (var x=0; x<input.length; x++) 
					{ //pętla po wszystkich input
						if(input[x].value == '')
							test = false;
						console.log(input[x].value);
					}
					
					//var Ankieta = document.getElementById('idAnkiety');
					//var idAnkiety = Ankieta.getElementsByTagName('text');
					
					if(test == true)
					{
						/*for (var x=0; x<input.length; x++) 
						{	
							if(x==0)
							{
								$.post("baza.php",
									{
										input:input[x].value,
										idAnkiety:idAnkiety[0].innerHTML,
										numerPytania:(x+1),
										tabAnkieta:0
									},
									"json");
									
								$.post("baza.php",
									{
										input:input[x].value,
										idAnkiety:idAnkiety[0].innerHTML,
										numerPytania:(x+1),
										tabAnkieta:1
									},
									"json");
							}else
							{
								$.post("baza.php",
									{
										input:input[x].value,
										idAnkiety:idAnkiety[0].innerHTML,
										numerPytania:(x+1),
										tabAnkieta:1
									},
									"json");
							}
						}*/
						location.href='wypelniona.html';
						//window.open ('wypelniona.html');
					}else
					{
						alert('Brak odpowiedzi na wszystkie pytania');
					}

				}
			]]></script>
            </body>
        </html>
    </xsl:template>	

    <xsl:template name="marketing.Ankieta">
	<div class="sekcjaPytan">
        <h3>Prosimy o poświęcenie kilku minut na wypełnienie ankiety:</h3>
			<table id="tabelka">
                <tbody>	
					<xsl:for-each select="marketing.Ankieta/pytania/string">
						<tr class="Lista">
						<td class="pytanie">
							<xsl:value-of select="."/>
						</td>
						</tr>
						<tr class="Lista">
							<td>
							<form action="..." class="oknoTekstowe">
								<input type="text" name="nazwa" size="30%"/>
							</form>
							</td>
						</tr>							
					</xsl:for-each>	
				</tbody>
			</table>
    </div>
	
	<div class="przycisk">
		<button type="submit" name="Wyślij" style="width:60px; height:30px;" onclick="zapisz()">Wyślij</button>
	</div>	
	<div id="idAnkiety" class="ankieta">
		<text style="visibility: hidden;">
			<xsl:value-of select="marketing.Ankieta/id__ankiety"/>
		</text>
	</div>
	</xsl:template>
	
</xsl:stylesheet>

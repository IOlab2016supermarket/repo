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
					var input = odpowiedzi.getElementsByClassName('Lista2'); //pobieramy wszystkie input z tabeli
					var czOdpowiedziNaPytania = odpowiedzi.getElementsByClassName('Lista2');
					for (var x=0; x<input.length; x++) 
						czOdpowiedziNaPytania[x].value = true;
					
					for (var x=0; x<input.length; x++)
					{
						var lista = input[x].getElementsByTagName('input');
						if(lista[0].type == 'text')
						{
							if(lista[0].value == '')
								czOdpowiedziNaPytania[x].value = false;
						}
						else
						{
							var czyZaznaczone = false;
							for (var l=0; l<lista.length; l++)
							{
								if(lista[l].checked)
									czyZaznaczone = true;
							}
							if(czyZaznaczone == false)
								czOdpowiedziNaPytania[x].value = false;
						}
					}					
					

					//var Ankieta = document.getElementById('idAnkiety');
					//var idAnkiety = Ankieta.getElementsByTagName('text');
					var czyWszystkiePytania = true;
					for (var x=0; x<czOdpowiedziNaPytania.length; x++) 
					{
						if(czOdpowiedziNaPytania[x].value == false)
							czyWszystkiePytania = false;
					}
					
					if(czyWszystkiePytania == true)
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
					<xsl:for-each select="marketing.Ankieta/pytania/marketing.AnkietaPytanie">
						<xsl:if test="czyOdpowiedzDoWyboru='false'">
							<tr class="Lista">
								<td class="pytanie">
									<xsl:value-of select="pytanie"/>
								</td>
							</tr>
							<tr class="Lista2">
								<td>
								<form action="..." class="oknoTekst">
									<input type="text" name="nazwa" size="30%" class="oknoTekstowe"/>
								</form>
								</td>
							</tr>
						</xsl:if>
						<xsl:if test="czyOdpowiedzDoWyboru='true'">
							<xsl:if test="czyWielokrotnegoWyboru='false'">
								<tr class="Lista">
									<td class="pytanie">
										<xsl:value-of select="pytanie"/>
									</td>
								</tr>
								<tr class="Lista2">
									<td>
									<form action="..." class="rbox">
										<xsl:for-each select="odpowiedziWybor/string">
											<input type='radio' name='group' ng-model='mValue' class="radiobox"/><xsl:value-of select="."/>
											<br/>
										</xsl:for-each>
									</form>
									</td>
								</tr>
							</xsl:if>
							<xsl:if test="czyWielokrotnegoWyboru='true'">
							<tr class="Lista">
								<td class="pytanie">
									<xsl:value-of select="pytanie"/>
								</td>
							</tr>
							<tr class="Lista2">
								<td>
								<form action="..." class="cbox">
									<xsl:for-each select="odpowiedziWybor/string">
										<input type='checkbox' name='groupcheck' class="checkbox"/><xsl:value-of select="."/>
										<br/>
									</xsl:for-each>
								</form>
								</td>
							</tr>
							</xsl:if>
						</xsl:if>
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

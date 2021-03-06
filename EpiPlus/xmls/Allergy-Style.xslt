<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version ="2.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">
<xsl:output method ="html" indent="yes"/>

<xsl:template match ="/">
	
	
<html lang ="en">
	<head>
		<title>EpiPlus</title>
		<meta charset="UTF-8">
		<meta name="description" content="Epilepsy website"/>
		<meta name="author" content="Lucia Isabel Marta Aleksandra" />
		<meta name="keywords" content="epilepsy" />
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		</meta>
	
		<link rel="stylesheet" href="./stylesheet.css" type="text/css" />
		<link rel="shortcut icon" href="../html_website/favicon.ico" type="image/x-icon"/>
		<link rel="icon" href="../html_website/favicon.ico" type="image/x-icon"/>
	
		<style type="text/css">
	
			.html { font-family : Arial ;}
			
			.header { text-align : center ;
					padding : 30px;
					background-color : #1abc9c;
					color : white;
					height: 20%;}
			
			.header h1{ font-size: 45px;}
	
			.body { width: auto; }
			
			
			
			
			.navbar { text-align : center;
					font-size: 30px;
					background-color: #08A16E;
					height : 5%;					}
			
			
			.aside{	float: left;
					background-color: #35D4B4;
					width: 35%;
					height: 75%;
					font-size: 20px;
					color: white;
			
			
						}
						
			.main{ background-color: 	#3ED6AB;
					float:right;
					width: 65%;
					height: 75%;
					margin: auto;
					text-align: center;
					font-size: 30px;
					color: white;
					}
			
			.footer { text-align: : right ; 
						background-color : #1abc9c ;
						color: white ;
						width: 100%;
						float: left;
						height: 10%;}
						
			a {text-decoration: none;
				margin: 10px;}
			
			a:hover { color: white;
					}
	
		</style>
	</head>
	
	<body>
	
		<div class = "header">
			<h1><b><i>Welcome to EpiPlus website! </i></b>
			<img src= "../html_website/healthicon.png" alt = "health symbol" width = "100" height = "100"/>
			</h1>
		
		</div>
		
		<div class = "navbar">
		<a href = "../html_website/epi.html">Home</a>
		<a href = "../html_website/epiumldiagram.html">UML diagram</a>
		<a href = "../html_website/epierdiagram.html">E-R diagram</a>
		<a href = "../html_website/epiusecasediagram.html">Use case diagram</a>
		<a href = "../html_website/epimockup.html">Mockup</a>
		
		</div>
		
		<div class = "aside">
		<h3>About the project</h3>
		<p>
		The EpiPlus application is created to help people struggling with epilepsy, in their everyday life.It has many functionalities. For example, EpiPlus can store medication's, patient's, doctor's data. It even provides recipes.
		It can create xml files in order to show data in more human-readable way. It also produces websites!
		</p>
		<p></p>
		
		<p><b><a href="../projects_documentation/EpiplusManual.pdf">Manual of the EpiPlus</a></b></p>
		
		
		
		<p><b>Authors of the EpiPlus app:</b></p>
		<p>Barnuevo Rotaeche Lucia,</p>
		<p>Isabel Mart??nez Torres,</p>
		<p>Lucena Vicente Marta,</p>
		<p>Aleksandra Krzemie??</p>
		<p><b>Enjoy our project!</b></p>

		</div>
		
		<div class= "main">
		<p><h3>The content of the given object allergy.</h3></p>
		<p><b>Id: <xsl:value-of select="//@id" /></b></p>
		<p><b>Name of the allergy: <xsl:value-of select="//@name" /></b></p>
		<p><b> Patients: </b> <xsl:value-of select= "//patients"/></p>
		
	
		</div>
		
		
		<div class="footer">
			<h2>EpiPlus team 2022 </h2>
		</div>
	
	
	
	
	
	</body>
</html>

</xsl:template>
	
</xsl:stylesheet>





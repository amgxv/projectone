<!DOCTYPE html>
<html lang="en">
<head>
<title>Restaurants</title>
</head>
<body>
Testeillo
<#list model["list"] as rest>
${rest.name}
</#list>
</body>
</html>
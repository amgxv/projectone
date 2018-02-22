<!DOCTYPE html>
<html lang="en">
<head>
    <title>Restaurants</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Estilo CSS -->
    <link rel="stylesheet" type="text/css" href="/css/stylo.css">

</head>


<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="/">Restaurants</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Inici <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/rest/api/v1/restaurants">API REST</a>
            </li>
            <!--
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
            -->
        </ul>

        </div>
    </div>
</nav>




<body>
<main role="main" class="container">
<div class="container">
    <h1 class="my-4"> Restaurants <small>a Palma de Mallorca</small></h1>
<div class="row">
<#if model??>
    <#list model["list"] as item>
<div class="col-lg-4 col-sm-6 portfolio-item">
<div class="card h-100">
<a href=<"#"><img id="imgg" class="card-img-top img-fluid" src="${item.images!""}" alt="\"></a>
<div class="card-body">
<h4 class="card-title">
<a href="#"}">${item.name!""}</a>
</h4>
<div class="row">
<div class="col-sm-12 mr-auto"><i class="material-icons align-bottom">place</i>${item.address!""}</div>
</div>
<div class="row">
<div class="col-sm-12 mr-auto"><i class="material-icons align-bottom">restaurant_menu</i>${item.type!""}</div>
</div>
<div class="row">
<div class="col-sm-12 mr-auto"><i class="material-icons align-bottom">star</i>${item.mitjana!""}</div>
</div>

</div>
</div>
    </div>
</#list>
</#if>
</div>
    </main>

</body>
</html>
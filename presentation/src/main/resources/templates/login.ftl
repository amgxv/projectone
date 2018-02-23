<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>

<h1>Log in</h1>

<p>You can use: joanet / undostres [ADMIN]</p>
<p>or : tomevet / quatre [USER]</p>

<form role="form" class="col-md-12" action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
        <input type="text" class="form-control input-lg" placeholder="Usuari" name="username"/>
    </div>
    <div class="form-group">
        <input type="password" class="form-control input-lg" placeholder="Password" name="password"/>
    </div>
    <div class="form-group">
        <button class="btn btn-primary btn-lg btn-block">Entra</button>
    </div>
</form>

<#-- Si hay error tira esto -->
<#if error.isPresent()>
<p>The email or password you have entered is invalid, try again.</p>
</#if>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Зачетная книжка</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="../static/app.css">
</head>
<body>

<div id="root">Загрузка...</div>


<div class="logo">
    <img src="../static/team.jpg" alt="" width="400" height="200">
</div>

<script src="https://fb.me/react-0.14.3.js"></script>
<script src="https://fb.me/react-dom-0.14.3.js"></script>

</br>


<form class="logfrom" action="/login" method="post">
<div class="err">
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger col-sm-2 " role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
</#if>
    <#if message??>
        <div class="alert alert-${messageType} " role="alert">
            ${message}
        </div>
    </#if>
</div>
<div class="container">
    <div class="row">
        <div class="col col-sm-3">
            <input type="text" class="form-control" id="username" name="username"
                   placeholder="Login">
        </div>
    </div>
    <div class="row mt-2">
        <div class="col col-sm-3">
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="Password">
        </div>
    </div>

    <div class="row mt-2 text-center">
        <div class="col">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
           <button type="submit" class="btn btn-primary">Sing In</button>
        </div>
    </div>
</div>

</form>

<script src="../static/script.js"></script>

<footer>
    <div class="foot">
    &copy;copyright 2018  Бубнов Максим
    </div>

</footer>
</body>
</html>
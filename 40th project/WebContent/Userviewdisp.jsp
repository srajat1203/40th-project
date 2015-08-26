<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
 <div class="jumbotron">
    <h1 align="center">Welcome!</h1>      
    <p align="center"> ${welcome}</p>
  </div>
  
  <h2>The Restaurants</h2>
  <ul class="list-group">
    <li class="list-group-item">${rlist}</li>
  </ul>
  
  
  <br><br>
  <a href="Updateuserdisp.jsp" class="btn btn-info" role="button">Update Your email</a>
  
  <br><br>
  <a href="Updatereview" class="btn btn-info" role="button">Update Your reviews</a>
  
</div>
</body>
</html>

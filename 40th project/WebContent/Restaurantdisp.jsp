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
    <h1 align = "center">${name}</h1>  
     <p align = "center">It is a ${desc} restaurant, and is located at ${address}</p>    
    <p align = "center">It has received ${ratings} ratings</p>
    <p align = "center">It has an average rating of ${avgrating}</p>
  </div>
  <h2>List of reviews</h2>
  <ul class="list-group">
  <li class="list-group-item">${rev}</li>
</ul> 


  <h2>Write comment</h2>
<form role="form" action = Userview>
    <div class="form-group">
      <label for="date">Date:</label>
      <input type="text" class="form-control" id="date" name = "date" placeholder="Enter date (yyyy/mm/dd)">
    </div>
    <div class="form-group">
      <label for="comment">comment:</label>
      <input type="text" class="form-control" id="comment" name = "comment" placeholder="Enter comment">
    </div>
    <div class="form-group">
      <label for="rating">rating:</label>
      <input type="number" step="0.1" class="form-control" id="rating" name = "rating" placeholder="Enter rating">
    </div>
    <button type="submit" class="btn btn-default">Submit comment</button>
  </form>


    
</div>
</body>
</html>

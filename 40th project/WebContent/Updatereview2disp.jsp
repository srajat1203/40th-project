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

<div class="container" >
  <h2>Enter new comment</h2>
  <form role="form" action = "Updatereview3">
    <div class="form-group">
      <label for="ncomment">Comment:</label>
      <input type="text" class="form-control" id="ncomment" name="ncomment" placeholder="Enter new comment">
    </div>
    <br><br>
    <h2>Enter new rating</h2>
    <div class="form-group">
      <label for="nrating">Rating:</label>
      <input type="number" step=0.01 class="form-control" id="nrating" name="nrating" placeholder="Enter new rating">
    </div>
    
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

</body>
</html>
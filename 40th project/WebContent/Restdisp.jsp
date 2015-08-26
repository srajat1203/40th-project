<!DOCTYPE html>
<html lang="en">
<head>
  <title>Update Restaurant</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
 <h2>Change Restaurant name</h2>  
  <form role="form" action="Rest" method = "post">
    <div class="form-group">
      <label for="id">ID:</label>
      <input type="number" class="form-control" id="id" name="id" placeholder="Enter restaurant id">
    </div>
    <div class="form-group">
      <label for="nname">New name:</label>
      <input type="text" class="form-control" id="nname" name="nname" placeholder="Enter new restaurant name">
    </div>
    <div class="form-group">
      <label for="nadd">New address:</label>
      <input type="text" class="form-control" id="nadd" name="nadd" placeholder="Enter new restaurant address">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</body>
</html>
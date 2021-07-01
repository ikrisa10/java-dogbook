<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create New Profile</title>
  </head>
  <body>
    <h1>Create New Profile</h1>
    <form method="POST" action="/dogs">
      <div>
        <label for="firstName">Dog First Name:</label>
        <input  type="text" id="firstName" name="firstName" value=""/>
      </div>
      <div>
        <label for="lastName">Dog Last Name:</label>
        <input  type="text" id="lastName" name="lastName" value="" />
      </div>
      <div>
        <label for="photoUrl">Dog Photo URL:</label>
        <input type="text" id="photoUrl" name="photoUrl" value="" />
      </div>
      <div>
        <label for="breed">Breed:</label>
        <input type="text" id="breed" name="breed" value="" />
      </div>
      <div>
        <label for="sex">Sex:</label>
        <select id="sex" name="sex">
          <option value="" disabled selected></option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Prefer Not Specify">Prefer Not Specify</option>
        </select>
      </div>
      <input type="submit" value="Add Profile" />
    </form>
  </body>
</html>
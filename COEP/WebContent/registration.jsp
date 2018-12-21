<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Flexbox Login/Register</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  
      <link rel="stylesheet" href="css1/style.css">

  
</head>

<body>

  <div class="flexbox" style="height: 100%;">
  <div class="content" style="width:1000px;">
    <div id="login" class="box show">
      <div class="field">
        <div class="sign-in">Application Form<br></div>
      </div>
      <div class="field" style="width: 40%">
        <label>Registration Id</label>
        <input type="text" readonly>
      </div>
      <div class="field" style="margin-left: 500px;float: right;margin-top: -73px;width: 40%;">
      <label>Name</label>
        <input type="text" readonly>
      </div>
      
     <div class="field" style="width: 40%">
      <label>Contact no</label>
        <input type="text"  readonly>
      </div>
      <div class="field" style="margin-left: 500px;float: right;margin-top: -73px;width: 40%;">
      <label>Parents Login Id</label>
        <input type="text" readonly>
      </div>
      <div class="field" style="width: 40%">
      <label>Branch</label>
        <select value="Branch" style="width: 150px; height: 30px;background-color: 	#D3D3D3;border: none;border-radius: 5px;">
         <option value="IT">IT</option>
          <option value="COMP">COMP</option>
          <option value="ENTC">ENTC</option>
          <option value="MECH">MECH</option>
          <option value="CIVIL">CIVIL</option>
           </select>   
      </div>
      <div class="field" style="margin-left: 500px;float: right;margin-top: -70px;width: 40%;">
      <label>Year</label>
       <br>
        <select value="Branch" style="width: 150px; height: 30px;background-color: 	#D3D3D3;border: none;border-radius: 5px;">
         <option value="FE">FE</option>
          <option value="SE">SE</option>
            <option value="TE">TE</option>
            <option value="BE">BE</option>
           </select>
      </div>
      
      <div class="field" style="width: 40%">
        <label>Parents Mobile</label>
        <input type="text" readonly>
      </div>
      
      <div class="field" style="margin-left: 500px;float: right;margin-top: -73px;width: 40%;">
      <label>CET/JEE/CGPA</label>
        <input type="text" readonly>
      </div>
      
      <div class="field" style="width: 40%">
        <label>Parents Mobile No</label>
        <input type="text" readonly>
      </div>
      
      <div style="margin-left: 20px;">
      <label>Gender</label><br>
      <input type="radio" name="gender" value="male"> Male<br>
      <input type="radio" name="gender" value="female"> Female<br>
      <input type="radio" name="gender" value="other"> Prefer Not to mention<br>
      </div>
      
      <div class="field">
        <button class="btn-sign-in">Sign In</button>
      </div>
    </div>
    <div id="register" class="box hide">
      <div class="field">
        <div class="sign-in">Register<br><small>Create your account</small></div>
      </div>
      <div class="field">
        <label>Username</label>
        <input type="text">
      </div>
      <div class="field">
        <label>Password</label>
        <input type="password">
      </div>
      <div class="field">
        <label>Confirm Password</label>
        <input type="password">
      </div>
      <div class="field">
        <a href="#" id="back" class="trouble">Back to login</a>
      </div>
      <div class="field" >
        <button class="btn-sign-in" >Register now</button>
      </div>
    </div>
  </div>
</div>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>

  

    <script  src="js1/index.js"></script>




</body>

</html>

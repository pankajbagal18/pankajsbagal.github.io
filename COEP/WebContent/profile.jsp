<%@ page import="General.QueryLayer" %>
<%@ page import="Login.User" %>
<%@ page import="java.sql.*" %>
<%@ page import="General.PdfData" %>
<%@ page import="Social.Generate_pdf" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Dashboard</title>

    <!-- Fontfaces CSS-->
    <link href="css3/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css3/theme.css" rel="stylesheet" media="all">
	<style>
.switch {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 25px;
}

.switch input {display:none;}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 15px;
  width: 15px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
.but {
	margin-left:300px;
    border-radius: 15px;
    background-color: #33cc33;
    border: none;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 13px;
    margin: 4px 2px;
    cursor: pointer;
}
    .but:hover{
        background-color: #196619;
    }
</style>
</head>

<body class="animsition">
<% 
User us=(User)session.getAttribute("user");


%>
    <div class="page-wrapper">
        <!-- HEADER MOBILE-->
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="index.html">
                            <img src="images/icon/logo.png" alt="CoolAdmin" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                            <!--<ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="index.html">Dashboard 1</a>
                                </li>
                                <!--<li>
                                    <a href="index2.html">Dashboard 2</a>
                                </li>
                                <li>
                                    <a href="index3.html">Dashboard 3</a>
                                </li>
                                <li>
                                    <a href="index4.html">Dashboard 4</a>
                                </li>
                            </ul>-->
                        </li>-->
                        <li>
                            <a href="chart.jsp">
                                <i class="fas fa-chart-bar"></i>YZ</a>
                        </li>
                        <li>
                            <a href="table.html">
                                <i class="fas fa-table"></i>Tables</a>
                        </li>
                        <li>
                            <a href="form.html">
                                <i class="far fa-check-square"></i>Forms</a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fas fa-calendar-alt"></i>Calendar</a>
                        </li>
                        <li>
                            <a href="map.html">
                                <i class="fas fa-map-marker-alt"></i>Maps</a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-copy"></i>Pages</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="login.html">Login</a>
                                </li>
                                <li>
                                    <a href="register.html">Register</a>
                                </li>
                                <li>
                                    <a href="forget-pass.html">Forget Password</a>
                                </li>
                            </ul>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-desktop"></i>UI Elements</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="button.html">Button</a>
                                </li>
                                <li>
                                    <a href="badge.html">Badges</a>
                                </li>
                                <li>
                                    <a href="tab.html">Tabs</a>
                                </li>
                                <li>
                                    <a href="card.html">Cards</a>
                                </li>
                                <li>
                                    <a href="alert.html">Alerts</a>
                                </li>
                                <li>
                                    <a href="progress-bar.html">Progress Bars</a>
                                </li>
                                <li>
                                    <a href="modal.html">Modals</a>
                                </li>
                                <li>
                                    <a href="switch.html">Switchs</a>
                                </li>
                                <li>
                                    <a href="grid.html">Grids</a>
                                </li>
                                <li>
                                    <a href="fontawesome.html">Fontawesome Icon</a>
                                </li>
                                <li>
                                    <a href="typo.html">Typography</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- END HEADER MOBILE-->

        <!-- MENU SIDEBAR-->
        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo.png" alt="COEP HOSTEL" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                    <li class="has-sub">
                            <a class="js-arrow" href="index.html">
                                <i class="fa fa-home"></i>Home</a>
                        </li>
                        <li class="active has-sub">
                            <a class="js-arrow" href="#">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                            <ul class="list-unstyled navbar__sub-list js-sub-list">
                              
                            
                            </ul>
                        </li>
                        <li class="has-sub">
                        <%
                        QueryLayer q1 = new QueryLayer();
                		//ResultSet res = q1.getAllSelectedFirstYear(80);
                		User user1 =new User();
                		 user1=(User)session.getAttribute("user");
                        boolean bool=q1.isInMeritList(user1.getId(),"sortedFirstYear");
                        
                       // long[] pref=q1.resubmitPref(user.getId());

                		if(bool==true)
                		{
                        %>
                            <a class="js-arrow" href="table.jsp">
                            <%
                		}
                		else
                		{
                            %>
                               <a class="js-arrow" href="error.jsp">
                            
                            
                            
                            <%
                		}
                            %>
                                <i class="fas fa-users"></i>Preference</a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="switch.jsp">
                                <i class="fas fa-line-chart"></i>Merit-List</a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="index.html">
                                <i class="fas fa-lock"></i>Logout</a>
                        </li>
                        
                        
                    </ul>
                </nav>
            </div>
        </aside>
        <!-- END MENU SIDEBAR-->

        <!-- PAGE CONTAINER-->
        <div class="page-container">
            <!-- HEADER DESKTOP-->
            <header class="header-desktop">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap">
                            
                            <div class="header-button">
                                <div class="noti-wrap">
                                    <div class="noti__item js-item-menu">
                                        
                                        <div class="mess-dropdown js-dropdown">
                                            <div class="mess__title">
                                                <p>You have 2 news message</p>
                                            </div>
                                            <div class="mess__item">
                                                <div class="image img-cir img-40">
                                                    <img src="images/icon/avatar-06.jpg" alt="Michelle Moreno" />
                                                </div>
                                                <div class="content">
                                                    <h6>Michelle Moreno</h6>
                                                    <p>Have sent a photo</p>
                                                    <span class="time">3 min ago</span>
                                                </div>
                                            </div>
                                            <div class="mess__item">
                                                <div class="image img-cir img-40">
                                                    <img src="images/icon/avatar-04.jpg" alt="Diane Myers" />
                                                </div>
                                                <div class="content">
                                                    <h6>Diane Myers</h6>
                                                    <p>You are now connected on message</p>
                                                    <span class="time">Yesterday</span>
                                                </div>
                                            </div>
                                            <div class="mess__footer">
                                                <a href="#">View all messages</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="noti__item js-item-menu">
                                        
                                        <div class="email-dropdown js-dropdown">
                                            <div class="email__title">
                                                <p>You have 3 New Emails</p>
                                            </div>
                                            <div class="email__item">
                                                <div class="image img-cir img-40">
                                                    <img src="images/icon/avatar-06.jpg" alt="Cynthia Harvey" />
                                                </div>
                                                <div class="content">
                                                    <p>Meeting about new dashboard...</p>
                                                    <span>Cynthia Harvey, 3 min ago</span>
                                                </div>
                                            </div>
                                            <div class="email__item">
                                                <div class="image img-cir img-40">
                                                    <img src="images/icon/avatar-05.jpg" alt="Cynthia Harvey" />
                                                </div>
                                                <div class="content">
                                                    <p>Meeting about new dashboard...</p>
                                                    <span>Cynthia Harvey, Yesterday</span>
                                                </div>
                                            </div>
                                            <div class="email__item">
                                                <div class="image img-cir img-40">
                                                    <img src="images/icon/avatar-04.jpg" alt="Cynthia Harvey" />
                                                </div>
                                                <div class="content">
                                                    <p>Meeting about new dashboard...</p>
                                                    <span>Cynthia Harvey, April 12,,2018</span>
                                                </div>
                                            </div>
                                            <div class="email__footer">
                                                <a href="#">See all emails</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="noti__item js-item-menu">
                                        <i class="zmdi zmdi-notifications"></i>
                                        <span class="quantity">3</span>
                                        <div class="notifi-dropdown js-dropdown">
                                            <div class="notifi__title">
                                                <p>You have 3 Notifications</p>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c1 img-cir img-40">
                                                    <i class="zmdi zmdi-email-open"></i>
                                                </div>
                                                <div class="content">
                                                    <p>You got a email notification</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c2 img-cir img-40">
                                                    <i class="zmdi zmdi-account-box"></i>
                                                </div>
                                                <div class="content">
                                                    <p>Your account has been blocked</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c3 img-cir img-40">
                                                    <i class="zmdi zmdi-file-text"></i>
                                                </div>
                                                <div class="content">
                                                    <p>You got a new file</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__footer">
                                                <a href="#">All notifications</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="account-wrap" style="float: right;">
                                    <div class="account-item clearfix js-item-menu">
                                        <div class="image">
                                            <img src="images/icon/avatar-01.jpg" alt="<%=us.getName() %>>" />
                                        </div>
                                        <div class="content">
                                            <a class="js-acc-btn" href="#"><%=us.getName() %></a>
                                        </div>
                                        <div class="account-dropdown js-dropdown">
                                            <div class="info clearfix">
                                                <div class="image">
                                                    <a href="#">
                                                        <img src="images/icon/avatar-01.jpg" alt="<%=us.getName() %>" />
                                                    </a>
                                                </div>
                                                <div class="content">
                                                    <h5 class="name">
                                                        <a href="#"><%=us.getName() %></a>
                                                    </h5>
                                                    <span class="email"><%=us.getEmail_id() %></span>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__body">
                                                <div class="account-dropdown__item">
                                                    <a href="#">
                                                        <i class="zmdi zmdi-account"></i>Account</a>
                                                </div>
                                                <div class="account-dropdown__item">
                                                    <a href="#">
                                                        <i class="zmdi zmdi-settings"></i>Setting</a>
                                                </div>
                                                <div class="account-dropdown__item">
                                                    <a href="#">
                                                        <i class="zmdi zmdi-money-box"></i>Billing</a>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__footer">
                                                <a href="#">
                                                    <i class="zmdi zmdi-power"></i>Logout</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- HEADER DESKTOP-->

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="overview-wrap">
                                    <h2 class="title-1">overview</h2>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="row m-t-25">
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c1">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="zmdi zmdi-account-o"></i>
                                            </div>
                                            <%
                                            QueryLayer q3=new QueryLayer();
                                            
                                            %>
                                            <div class="text">
                                                <h2><%= q3.getRank(us.getId())%></h2> <!--Write jsp code here -->
                                                <span>Your Rank(<%=us.getBranch() %>)</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c2">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="fa fa-handshake-o"></i>
                                            </div>
                                            <div class="text">
                                                <h2><%= q3.getGivePref(us.getId())%></h2>
                                                <span>Given preferences</span><!--Write jsp code here-->
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c3">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="fa fa-handshake-o"></i>
                                            </div>
                                            <div class="text">
                                                <h2><%= q3.getRecPref(us.getId())%></h2>
                                                <span>Preferences received</span><!--Write jsp code here-->
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-lg-3">
                                <div class="overview-item overview-item--c4">
                                    <div class="overview__inner">
                                        <div class="overview-box clearfix">
                                            <div class="icon">
                                                <i class="fa fa-bell"></i>
                                            </div>
                                            <div class="text">
                        
                                                <span>Merit list will be updated at 5 PM everyday</span>
                                            </div>
                                        </div>
                                        <div class="overview-chart">
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                 
                         <div class="row" style="width: 140%;">
                            <div class="col-lg-9">
                                <h2 class="title-1 m-b-25">Given Preferences</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                                <th>Registration ID</th>
                                                <th>Name</th>
                                                <th class="text-left">Email ID</th>
                                                <th class="text-left">Branch</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                        QueryLayer q=new QueryLayer();
                                        User user=new User();
                                        user=(User)session.getAttribute("user");
                                        ResultSet rs=q.showGivenPreferences(user.getId());
                                        
                                        long [] pref=new long[3];
                                        
                                        int i=0;
                                        while(rs.next())
                                        {
                                        	
                                        	if(rs.getLong(2)!=0)
                                        	{
                                        		pref[0]=rs.getLong(2);
                                        	}
                                        	if(rs.getLong(3)!=0)
                                        	{
                                        		pref[1]=rs.getLong(3);
                                        	}
                                        	if(rs.getLong(4)!=0)
                                        	{
                                        		pref[2]=rs.getLong(4);
                                        	}
                                        	
                                        	
                                        }
                                        while(i<pref.length)
                                        {
                                        String name="",emailId="",branch="";
                                        ResultSet res=	q.getStudentProfile(pref[i],"firstYear");
                                        if(res.next())
                                        {
                                         name=res.getString("name");
                                         emailId=res.getString("email_id");
                                         branch=res.getString("branch");
                                         
                                        }
                                       if(pref[i]!=0)
                                       {
                                        %>
                                        
                                                       
                                        
                                        
                                            <tr>
                                                <td><%=pref[i] %></td>
                                                <td><%=name %></td>
                                                <td class="text-left"><%=emailId %></td>
                                                <td class="text-left"><%=branch %></td>
                                            </tr>
                                       <%
                                       }
                                       i++;
                                        
                                        }
                                       %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            
                        </div>
                         <div class="row" style="width: 140%;">
                            <div class="col-lg-9">
                                <h2 class="title-1 m-b-25">Received Preferences</h2>
                                <div class="table-responsive table--no-card m-b-40">
                                    <table class="table table-borderless table-striped table-earning">
                                        <thead>
                                            <tr>
                                                <th>Registration ID</th>
                                                <th>Name</th>
                                                <th class="text-left">Email ID</th>
                                                <th class="text-left">Branch</th>
                                                <th>Status</th>
                                                
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                        
                                      ResultSet res1=  q.showReceivedPreferences(user.getId());
										long [] pref1=new long[3];
                                        
                                        int j=0;
                                        while(res1.next())
                                        {
                                        	
                                        	if(res1.getLong(2)!=0)
                                        	{
                                        		pref1[0]=res1.getLong(2);
                                        	}
                                        	if(res1.getLong(3)!=0)
                                        	{
                                        		pref1[1]=res1.getLong(3);
                                        	}
                                        	if(res1.getLong(4)!=0)
                                        	{
                                        		pref1[2]=res1.getLong(4);
                                        	}
                                        	
                                        	
                                        }
                                        while(j<pref1.length)
                                        {
                                        String name="",emailId="",branch="";
                                        ResultSet res=	q.getStudentProfile(pref1[j],"firstYear");
                                        if(res.next())
                                        {
                                         name=res.getString("name");
                                         emailId=res.getString("email_id");
                                         branch=res.getString("branch");
                                         
                                        }
                                        if(pref1[j]!=0)
                                        {
                                        %>
                                        
                                            <tr>
                                                <td><%=pref1[j] %></td>
                                                <td><%=name %></td>
                                                <td class="text-left"><%=emailId %></td>
                                                <td class="text-left"><%=branch %></td>
                                                <td>
                             					 <form name=form1 action="ConfirmRequest" method="post">
                                                 <label class="switch">
  												<input type=checkbox name=ckb value=<%=pref1[j] %> unchecked>
												  <span class="slider round"></span>
												</label>
                                                
                                                </td>
                                            </tr>
                                         <% 
                                        }
                                         j++;
                                        }
                                         %>
                                        </tbody>
                                    </table>
                                    <input type="submit" onclick='return chkcontrol()' value="Submit" class="but" style="margin-left:45%;">
                                    </form>
                                 <% 
                                 boolean bool1=false;
                                 	if((int)session.getAttribute("flag")==1)
                                 	 bool1=true;
                                 if(bool1 )
                                 {
                                	 
                                	 QueryLayer qq=new QueryLayer();
                                	 PdfData pd=new PdfData();
                                	 pd.setAppid(us.getId());
                                	 pd.setMarks(us.getMarks());
                                	 pd.setMis(us.getId());
                                	 pd.setName(us.getName());
                                	 long[] id=new long[5];
                                	 pd.setRank(qq.getRank(us.getId()));
									if(qq.sendAllocationStatus(us.getId())!=null)
									{
                                	  id=qq.sendAllocationStatus(us.getId());

                                	 pd.setRoomid((int)id[0]);
                                	 pd.setRm1(id[1]);

                                	 pd.setRm2(id[2]);
                                	 pd.setRm3(id[3]);
                                	 pd.setRm4(id[4]);

                                	 
                                	 try
                                	 {
                                	 Generate_pdf gp=new Generate_pdf();
                                	 gp.generatePDF("/home/shubham/eclipse-workspace/COEP/WebContent/first.pdf", pd);
                                	 }catch(Exception e){}
                                	 
                                	//response.sendRedirect("/home/shubham/eclipse/first.pdf");
                                 %>                             
                                     <a href="temp.jsp">Click here</a>
                                     <%
									}
									}
                                     %>
                                </div>
                            </div>
                            
                        </div>
               
                        
                        <div class="row">
                            <div class="col-md-12">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN CONTENT-->
            <!-- END PAGE CONTAINER-->
        </div>

    </div>

    <!-- Jquery JS-->
    <script src="vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="vendor/slick/slick.min.js">
    </script>
    <script src="vendor/wow/wow.min.js"></script>
    <script src="vendor/animsition/animsition.min.js"></script>
    <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
    </script>
    <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="vendor/counter-up/jquery.counterup.min.js">
    </script>
    <script src="vendor/circle-progress/circle-progress.min.js"></script>
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="vendor/select2/select2.min.js">
    </script>

    <!-- Main JS-->
    <script src="js3/main.js"></script>
<script type="text/javascript"> 
function chkcontrol() {
var sum=0;
for(var i=0; i < 3; i++){

if(document.form1.ckb[i].checked){
sum = sum + 1;
}
}

if(sum >1){


alert("You cannot select more than 1 checkboxes") 
return false;
}
}
</script>
</body>

</html>
<!-- end document-->

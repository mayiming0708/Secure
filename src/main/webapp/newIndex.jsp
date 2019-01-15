<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>AdminLTE 2 | Dashboard</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!-- Bootstrap 3.3.7 -->
		<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
		<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
		<!-- Morris chart -->
		<link rel="stylesheet" href="bower_components/morris.js/morris.css">
		<!-- jvectormap -->
		<link rel="stylesheet" href="bower_components/jvectormap/jquery-jvectormap.css">
		<!-- Date Picker -->
		<link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
		<!-- Daterange picker -->
		<link rel="stylesheet" href="bower_components/bootstrap-daterangepicker/daterangepicker.css">
		<!-- bootstrap wysihtml5 - text editor -->
		<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
		<link rel="stylesheet" type="text/css" href="bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css" />

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

		<!-- Google Font -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
		<link rel="stylesheet" type="text/css" href="dist/css/index.css" />
		<style>
			.sidebar .user-panel {
				display: none;
			}
			
			.sidebar .sidebar-form {
				display: none;
			}
			
			.sidebar-menu.tree li.header {
				display: none;
			}
			
			.temporary-task {
				margin-bottom: 20px;
				background: #fff;
				box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
				border-radius: 3px;
				height: 300px;
			}
			
			.temporary-task .title {
				line-height: 35px;
				padding: 0 10px;
				font-size: 20px;
				color: #444;
			}
			
			.pull-left.header .fa-inbox {
				color: lightseagreen;
			}
			
			.nav-tabs-custom {
				/*box-shadow: 0 0 2px rgba(0,0,0,0.3);*/
				border-radius: 10px;
			}
			
			#tempory-task .flow {
				margin-top: 18px;
				position: relative;
				display: inline-block;
				float: left;
			}
			
			#tempory-task .line {
				position: absolute;
				width: 2px;
				border-radius: 5px;
				height: 240px;
				background-color: lightblue;
				left: 78%;
			}
			
			#tempory-task .flow-img {
				display: inline-block;
				list-style-type: none;
				position: relative;
				z-index: 2;
			}
			
			#tempory-task .flow-img li {
				height: 78px;
			}
		</style>
	</head>

	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">

			<header class="main-header">
				<!-- Logo -->
				<a href="#" class="logo">
					<!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>WE</b>B</span>
					<!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>WEB</b>SOCK</span>
				</a>
				<!-- Header Navbar: style can be found in header.less -->
				<nav class="navbar navbar-static-top">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>

					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<!-- Messages: style can be found in dropdown.less-->
							<li class="dropdown messages-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-envelope-o"></i>
									<span class="label label-success">4</span>
								</a>
								<ul class="dropdown-menu">
									<li class="header">You have 4 messages</li>
									<li>
										<!-- inner menu: contains the actual data -->
										<ul class="menu">
											<li>
												<!-- start message -->
												<a href="#">
													<div class="pull-left">
														<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
													</div>
													<h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
													<p>Why not buy a new awesome theme?</p>
												</a>
											</li>
											<!-- end message -->
											<li>
												<a href="#">
													<div class="pull-left">
														<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
													</div>
													<h4>
                        AdminLTE Design Team
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
													<p>Why not buy a new awesome theme?</p>
												</a>
											</li>
											<li>
												<a href="#">
													<div class="pull-left">
														<img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
													</div>
													<h4>
                        Developers
                        <small><i class="fa fa-clock-o"></i> Today</small>
                      </h4>
													<p>Why not buy a new awesome theme?</p>
												</a>
											</li>
											<li>
												<a href="#">
													<div class="pull-left">
														<img src="dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
													</div>
													<h4>
                        Sales Department
                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                      </h4>
													<p>Why not buy a new awesome theme?</p>
												</a>
											</li>
											<li>
												<a href="#">
													<div class="pull-left">
														<img src="dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
													</div>
													<h4>
                        Reviewers
                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                      </h4>
													<p>Why not buy a new awesome theme?</p>
												</a>
											</li>
										</ul>
									</li>
									<li class="footer">
										<a href="#">See All Messages</a>
									</li>
								</ul>
							</li>
							<!-- Notifications: style can be found in dropdown.less -->
							<li class="dropdown notifications-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-bell-o"></i>
									<span class="label label-warning">10</span>
								</a>
								<ul class="dropdown-menu">
									<li class="header">You have 10 notifications</li>
									<li>
										<!-- inner menu: contains the actual data -->
										<ul class="menu">
											<li>
												<a href="#">
													<i class="fa fa-users text-aqua"></i> 5 new members joined today
												</a>
											</li>
											<li>
												<a href="#">
													<i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
												</a>
											</li>
											<li>
												<a href="#">
													<i class="fa fa-users text-red"></i> 5 new members joined
												</a>
											</li>
											<li>
												<a href="#">
													<i class="fa fa-shopping-cart text-green"></i> 25 sales made
												</a>
											</li>
											<li>
												<a href="#">
													<i class="fa fa-user text-red"></i> You changed your username
												</a>
											</li>
										</ul>
									</li>
									<li class="footer">
										<a href="#">View all</a>
									</li>
								</ul>
							</li>
							<!-- Tasks: style can be found in dropdown.less -->
							<li class="dropdown tasks-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-flag-o"></i>
									<span class="label label-danger">9</span>
								</a>
								<ul class="dropdown-menu">
									<li class="header">You have 9 tasks</li>
									<li>
										<!-- inner menu: contains the actual data -->
										<ul class="menu">
											<li>
												<!-- Task item -->
												<a href="#">
													<h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
													<div class="progress xs">
														<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
															<span class="sr-only">20% Complete</span>
														</div>
													</div>
												</a>
											</li>
											<!-- end task item -->
											<li>
												<!-- Task item -->
												<a href="#">
													<h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
													<div class="progress xs">
														<div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
															<span class="sr-only">40% Complete</span>
														</div>
													</div>
												</a>
											</li>
											<!-- end task item -->
											<li>
												<!-- Task item -->
												<a href="#">
													<h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
													<div class="progress xs">
														<div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
															<span class="sr-only">60% Complete</span>
														</div>
													</div>
												</a>
											</li>
											<!-- end task item -->
											<li>
												<!-- Task item -->
												<a href="#">
													<h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
													<div class="progress xs">
														<div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
															<span class="sr-only">80% Complete</span>
														</div>
													</div>
												</a>
											</li>
											<!-- end task item -->
										</ul>
									</li>
									<li class="footer">
										<a href="#">View all tasks</a>
									</li>
								</ul>
							</li>
							<!-- User Account: style can be found in dropdown.less -->
							<li class="dropdown user user-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
									<span class="hidden-xs">Alexander Pierce</span>
								</a>
								<ul class="dropdown-menu">
									<!-- User image -->
									<li class="user-header">
										<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

										<p>
											Alexander Pierce - Web Developer
											<small>Member since Nov. 2012</small>
										</p>
									</li>
									<!-- Menu Body -->
									<li class="user-body">
										<div class="row">
											<div class="col-xs-4 text-center">
												<a href="#">Followers</a>
											</div>
											<div class="col-xs-4 text-center">
												<a href="#">Sales</a>
											</div>
											<div class="col-xs-4 text-center">
												<a href="#">Friends</a>
											</div>
										</div>
										<!-- /.row -->
									</li>
									<!-- Menu Footer-->
									<li class="user-footer">
										<div class="pull-left">
											<a href="#" class="btn btn-default btn-flat">Profile</a>
										</div>
										<div class="pull-right">
											<a href="#" class="btn btn-default btn-flat">Sign out</a>
										</div>
									</li>
								</ul>
							</li>
							<!-- Control Sidebar Toggle Button -->
							<li>
								<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- Sidebar user panel -->
					<div class="user-panel">
						<div class="pull-left image">
							<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
						</div>
						<div class="pull-left info">
							<p>Alexander Pierce</p>
							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>
					<!-- search form -->
					<form action="#" method="get" class="sidebar-form">
						<div class="input-group">
							<input type="text" name="q" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
						</div>
					</form>
					<!-- /.search form -->
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu" data-widget="tree">

						<li class="active">
							<a href="newIndex.html">
								<i class="fa fa-th"></i> <span>首页</span>
								<span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
							</a>
						</li>
						<li>
							<a href="newIndex.html">
								<i class="fa fa-calendar"></i> <span>操作日志</span>
								<span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
							</a>
						</li>
					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>

			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<section class="content-header">

				</section>

				<!-- Main content -->
				<section class="content">
					<!-- Small boxes (Stat box) -->
					<!--<div class="row">
      	<div class="col-lg-6">
      		<div class="temporary-task">
      			<div class="title">
      				<i class="fa fa-calendar-check-o"></i> 下发临时任务
      			</div>
      		</div>
      	</div>
      </div>-->
					<!--第一层row-->
					
					<div class="row">
						<div class="col-lg-3 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>150<sup style="font-size: 20px">次</sup></h3>

									<p>下发任务次数</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-green">
								<div class="inner">
									<h3>53<sup style="font-size: 20px">个</sup></h3>

									<p>检测网站个数</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>44<sup style="font-size: 20px">个</sup></h3>

									<p>发现漏洞个数</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-red">
								<div class="inner">
									<h3>65<sup style="font-size: 20px">min</sup></h3>

									<p>平均检索时间</p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>
					<div class="row">
						<section class="col-lg-6 connectedSortable">
							<div class="box box-success  check-site">
									<div class="check-site-title">
										<span>下发任务</span>
										<i class="fa fa-map-o"></i>
									</div>
										<div class="check-site-chart">
											<div class="chart tab-pane active temporary-task-total" id="tempory-task" style="position: relative; height: 343px;">
										<div class="left-part">
											<div class="form-groups" id="task-type">
												<label for="name">任务类型<p class="prompt"></p></label>
												<select name="task-type-choose">
													<option value="临时任务">临时任务</option>
													<option value="周期任务">周期任务</option>
												</select>
											</div>
											<div class="form-groups" id="tempory-site-name">
												<label for="name">站点名称<p class="prompt">(*站点名称为必填项，多个站点名请以逗号分隔)</p></label>
												<input type="text" class="form-control" id="name" placeholder="请输入站点名称">
											</div>
											
											<div class="choose-title">检测项</div>
											<ul class="choose-item clearfix">
												<li data='0'>暗链</li>
												<li data='0'>挂马</li>
												<li data='0'>关键词</li>
												<li data='0'>SQL注入漏洞</li>
												<li data='0'>XSS跨站脚本漏洞</li>
												<li data='0'>应用漏洞</li>
												<li data='0'>信息泄露</li>
												<li data='0'>CGI漏洞</li>
												<li data='0'>CSRF跨站请求伪造漏洞</li>
												<li data='0'>表单破解漏洞</li>
											</ul>
										</div>
										<div class="commit-btn" id="tempory-task-btn">
											提交任务
										</div>
										<div class="cover">
									
								</div>
								<div class="pop-alert">
									<div class="title"><i class="fa fa-warning"></i><span>下发任务失败</span></div>
									<div class="pop-alert-content">
										
									</div>
									<button type="button" class="btn btn-block btn-primary btn-flat">确定</button>
								</div>
									</div>
									
										</div>
							</div>
						</section>
						<section class="col-lg-6 connectedSortable">
									<div class="nav-tabs-custom temporary-task-total">
								<!-- Tabs within a box -->
								<ul class="nav nav-tabs pull-right">
									<li class="active">
										<a href="#site-faq" data-toggle="tab">网站风险分布</a>
									</li>
									<li>
										<a href="#bug-faq" data-toggle="tab">漏洞风险分布</a>
									</li>
									<!--<li class="pull-left header"><i class="fa fa-inbox"></i> 下发临时任务</li>-->
								</ul>
								<div class="tab-content no-padding">
									<!-- Morris chart - Sales -->
									<div class="chart tab-pane active" id="site-faq" style="position: relative; height: 300px;">
									<div class="site-faq-left">
										<div class="info">
											<p class="title">网站风险值排名</p>
											<ul class="rank-list">
												<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">http://testphp.vulnweb.com</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>68</span>
												</li>
													<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">http://testphp.vulnweb.com</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>65</span>
												</li>
													<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">http://testphp.vulnweb.com</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>60</span>
												</li>
											</ul>
										</div>
									</div>
										<div class="site-faq-right" id="site-faq-chart">
											
										</div>
									</div>
									<div class="chart tab-pane clearfix" id="bug-faq" style="position: relative; height: 300px;">
										<div class="bug-faq-left">
											<div class="info">
											<p class="title">高危漏洞类型排名</p>
											<ul class="rank-list">
												<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">暗链</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>68</span>
												</li>
													<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">挂马</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>65</span>
												</li>
													<li class="clearfix"><img src="image/canteen-top1.png">
													<div class="center">
														<div class="site-name">sql注入</div>
													</div>
													<div class="progress">
														<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
															<span class="sr-only">40% 完成</span>
														</div>
													</div><span>60</span>
												</li>
											</ul>
										</div>
										</div>
										<div class="bug-faq-right" id="bug-faq-chart2">
											
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
					<div class="row">
						<section class="col-lg-8 connectedSortable">
							<div class="nav-tabs-custom bug-type-left">
								<ul class="nav nav-tabs pull-right">
									<li class="active">
										<a href="#bug-type-chart2" data-toggle="tab">Area</a>
									</li>
									<li>
										<a href="#bug-type-chart4" data-toggle="tab">Dount</a>
									</li>
								</ul>
								<div class="tab-content no-padding">
									<div class="chart tab-pane active bug-type-chart2" id="bug-type-chart2" style="position: relative; height: 400px;">
										
									</div>
									<div class="chart tab-pane bug-type-chart4" id="bug-type-chart4" style="position: relative; height: 400px;">
										
									</div>
								</div>
							</div>
						</section>
						<section class="col-lg-4 connectedSortable">
							<div class="box box-success bug-type-right">
								<div class="title">
									网站漏洞类型占比
								</div>
								<div class="bug-type-chart3" id="bug-type-chart3">
									
								</div>
							</div>
							
						</section>
					</div>
					<div class="row">
						<section class="col-lg-6 connectedSortable">
							<!-- 临时任务检测进度-->
							<div class="box box-success task-progress">
								<div class="title">
									<i class="fa fa-inbox"></i>获取临时任务进度
								</div>
								<div class="box-body">
									<table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>任务ID</th>
												<th>总站点数</th>
												<th>检测进度</th>
												<th>检测成功站点数</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>6</td>
												<td>
													<div>http://a.com<span class="progressing">检测中</span></div>
													<div>http://a.com<span class="done">检测成功</span></div>
												</td>
												<td>6</td>
											</tr>
											<tr>
												<td>1</td>
												<td>6</td>
												<td>
													<div>http://a.com<span class="progressing">检测中</span></div>
													<div>http://a.com<span class="done">检测成功</span></div>
												</td>
												<td>6</td>
											</tr>
											<tr>
												<td>1</td>
												<td>6</td>
												<td>
													<div>http://a.com<span class="progressing">检测中</span></div>
													<div>http://a.com<span class="done">检测成功</span></div>
												</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.0</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.1</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.2</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.3</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.4</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.5</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.6</td>
												<td>Win 95+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.7</td>
												<td>Win 98+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Mozilla 1.8</td>
												<td>Win 98+ / OSX.1+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Seamonkey 1.1</td>
												<td>Win 98+ / OSX.2+</td>
												<td>6</td>
											</tr>
											<tr>
												<td>Gecko</td>
												<td>Epiphany 2.20</td>
												<td>Gnome</td>
												<td>6</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>

						</section>
						<!-- /.Left col -->
						<!-- right col (We are only adding the ID to make the widgets sortable)-->
						<section class="col-lg-6 connectedSortable">
							<div class="box box-solid bg-teal-gradient">
								<div class="box-header">
									<i class="fa fa-th"></i>

									<h3 class="box-title">Sales Graph</h3>

									<div class="box-tools pull-right">
										<button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
										<button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
									</div>
								</div>
								<div class="box-body border-radius-none">
									<div class="chart" id="line-chart" style="height: 250px;"></div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer no-border">
									<div class="row">
										<div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
											<input type="text" class="knob" data-readonly="true" value="20" data-width="60" data-height="60" data-fgColor="#39CCCC">

											<div class="knob-label">Mail-Orders</div>
										</div>
										<!-- ./col -->
										<div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
											<input type="text" class="knob" data-readonly="true" value="50" data-width="60" data-height="60" data-fgColor="#39CCCC">

											<div class="knob-label">Online</div>
										</div>
										<!-- ./col -->
										<div class="col-xs-4 text-center">
											<input type="text" class="knob" data-readonly="true" value="30" data-width="60" data-height="60" data-fgColor="#39CCCC">

											<div class="knob-label">In-Store</div>
										</div>
										<!-- ./col -->
									</div>
									<!-- /.row -->
								</div>
								<!-- /.box-footer -->
							</div>

						</section>
					</div>
					<!--<div class="row">
						<section class="col-lg-12 connectedSortable">
							<div class="task-report box clearfix">
								<div class="title">
									<i class="fa fa-inbox"></i> 检测结果(以任务为单位)
								</div>
								<ul class="left-part">
									<li left-part-item>
										<div class="site-name"><i class="fa fa-warning"></i>http://www.aaa.com</div>
										<div class="warn clearfix">
											<div class="fengxian">风险值<span>67</span></div>
											<div class="url">共发现url<span>74</span></div>

										</div>
										<div class="distribute"><br />
											<div class="list">
												<div class="box-body">
													<table id="result-list" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th>监控类型</th>
																<th>检测选项</th>
																<th>高危</th>
																<th>中危</th>
																<th>低危</th>
																<th>信息</th>
																<th>总计</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>漏洞</td>
																<td>表单弱口令</td>
																<td>2</td>
																<td>1</td>
																<td>0</td>
																<td>0</td>
																<td>3</td>
															</tr>
															<tr>
																<td>漏洞</td>
																<td>表单弱口令</td>
																<td>2</td>
																<td>1</td>
																<td>0</td>
																<td>0</td>
																<td>3</td>
															</tr>
															<tr>
																<td>漏洞</td>
																<td>表单弱口令</td>
																<td>2</td>
																<td>1</td>
																<td>0</td>
																<td>0</td>
																<td>3</td>
															</tr>
														</tbody>
													</table>
												</div>

											</div>
											<div class="distribute-chart">
												<div class="row">
													<div class="col-sm-6">
														<div class="distribute-chart-left" id="result-chart">

														</div>
													</div>
													<div class="col-sm-6">
														<div class="distribute-chart-right">
															<i class="fa fa-warning"></i>网站存在一个漏洞（1个高危，0个中危，0个低危）；建议进行防御加固，漏洞修复处理
														</div>
													</div>
												</div>
											</div>
										</div>
									</li>
								</ul>
								<div class="right-part">

								</div>
							</div>
						</section>

					</div>-->
					<!--<div class="row">
						<section class="col-lg-12 connectedSortable">
							<div class="box-body">
								<table id="tableFour" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>任务ID</th>
											<th>总站点数</th>
											<th>检测进度</th>
											<th>检测成功站点数</th>
										</tr>
									</thead>
								</table>
							</div>
						</section>
					</div>-->
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Version</b> 2.4.0
				</div>
				<strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights reserved.
			</footer>

			<!-- Control Sidebar -->
			<aside class="control-sidebar control-sidebar-dark">
				<!-- Create the tabs -->
				<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
					<li>
						<a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
					</li>
					<li>
						<a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a>
					</li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
					<!-- Home tab content -->
					<div class="tab-pane" id="control-sidebar-home-tab">
						<h3 class="control-sidebar-heading">Recent Activity</h3>
						<ul class="control-sidebar-menu">
							<li>
								<a href="javascript:void(0)">
									<i class="menu-icon fa fa-birthday-cake bg-red"></i>

									<div class="menu-info">
										<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

										<p>Will be 23 on April 24th</p>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<i class="menu-icon fa fa-user bg-yellow"></i>

									<div class="menu-info">
										<h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

										<p>New phone +1(800)555-1234</p>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

									<div class="menu-info">
										<h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

										<p>nora@example.com</p>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<i class="menu-icon fa fa-file-code-o bg-green"></i>

									<div class="menu-info">
										<h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

										<p>Execution time 5 seconds</p>
									</div>
								</a>
							</li>
						</ul>
						<!-- /.control-sidebar-menu -->

						<h3 class="control-sidebar-heading">Tasks Progress</h3>
						<ul class="control-sidebar-menu">
							<li>
								<a href="javascript:void(0)">
									<h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="label label-danger pull-right">70%</span>
              </h4>

									<div class="progress progress-xxs">
										<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<h4 class="control-sidebar-subheading">
                Update Resume
                <span class="label label-success pull-right">95%</span>
              </h4>

									<div class="progress progress-xxs">
										<div class="progress-bar progress-bar-success" style="width: 95%"></div>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<h4 class="control-sidebar-subheading">
                Laravel Integration
                <span class="label label-warning pull-right">50%</span>
              </h4>

									<div class="progress progress-xxs">
										<div class="progress-bar progress-bar-warning" style="width: 50%"></div>
									</div>
								</a>
							</li>
							<li>
								<a href="javascript:void(0)">
									<h4 class="control-sidebar-subheading">
                Back End Framework
                <span class="label label-primary pull-right">68%</span>
              </h4>

									<div class="progress progress-xxs">
										<div class="progress-bar progress-bar-primary" style="width: 68%"></div>
									</div>
								</a>
							</li>
						</ul>
						<!-- /.control-sidebar-menu -->

					</div>
					<!-- /.tab-pane -->
					<!-- Stats tab content -->
					<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
					<!-- /.tab-pane -->
					<!-- Settings tab content -->
					<div class="tab-pane" id="control-sidebar-settings-tab">
						<form method="post">
							<h3 class="control-sidebar-heading">General Settings</h3>

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

								<p>
									Some information about this general settings option
								</p>
							</div>
							<!-- /.form-group -->

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Allow mail redirect
              <input type="checkbox" class="pull-right" checked>
            </label>

								<p>
									Other sets of options are available
								</p>
							</div>
							<!-- /.form-group -->

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Expose author name in posts
              <input type="checkbox" class="pull-right" checked>
            </label>

								<p>
									Allow the user to show his name in blog posts
								</p>
							</div>
							<!-- /.form-group -->

							<h3 class="control-sidebar-heading">Chat Settings</h3>

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Show me as online
              <input type="checkbox" class="pull-right" checked>
            </label>
							</div>
							<!-- /.form-group -->

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Turn off notifications
              <input type="checkbox" class="pull-right">
            </label>
							</div>
							<!-- /.form-group -->

							<div class="form-group">
								<label class="control-sidebar-subheading">
              Delete chat history
              <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
            </label>
							</div>
							<!-- /.form-group -->
						</form>
					</div>
					<!-- /.tab-pane -->
				</div>
			</aside>
			<!-- /.control-sidebar -->
			<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
			<div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->

		<!-- jQuery 3 -->
		<script src="bower_components/jquery/dist/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="bower_components/jquery-ui/jquery-ui.min.js"></script>
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<!-- Bootstrap 3.3.7 -->
		<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- Morris.js charts -->
		<script src="bower_components/raphael/raphael.min.js"></script>
		<script src="bower_components/morris.js/morris.min.js"></script>
		<!-- Sparkline -->
		<script src="bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
		<!-- jvectormap -->
		<!--<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>-->
		<!-- jQuery Knob Chart -->
		<script src="bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
		<!-- daterangepicker -->
		<script src="bower_components/moment/min/moment.min.js"></script>
		<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
		<!-- datepicker -->
		<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
		<!-- Bootstrap WYSIHTML5 -->
		<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
		<!-- Slimscroll -->
		<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<!-- FastClick -->
		<script src="bower_components/fastclick/lib/fastclick.js"></script>
		<!--表格插件-->
		<script src="bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- AdminLTE App -->
		<script src="dist/js/adminlte.min.js"></script>
		<script src="dist/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
		<script src="dist/js/pages/dashboard.js"></script>
		<!-- AdminLTE for demo purposes -->
		<script src="dist/js/demo.js"></script>
		<script type="text/javascript">
			$(function() {
				//resize ehcarts
//				var bugTypeChart4='';
//				
//				$("。bug-type-left li").on('click',function(){
//					alert('hello');
//				})
				var temAlert = $(".temporary-task-total .pop-alert-content");
				var temCover = $(".temporary-task-total .cover");
				var temPop = $(".temporary-task-total .pop-alert");
				var temBtn = $(".temporary-task-total button");
				var temTitle = $(".temporary-task-total  .pop-alert .title");
				var taskType= $(".temporary-task-total select");
			

				//下发临时任务检测项
				$(".choose-item li").click(function() {
					if($(this).hasClass('active')) {
						$(this).removeClass('active');
						$(this).attr('data', '0');
					} else {
						$(this).addClass('active');
						$(this).attr('data', '1');
					}

				});
				//点击提交按钮
				$(".commit-btn").click(function() {
					var choose = '';
					var reg = '(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]';
					var site = $("#tempory-site-name #name");
					var siteVal = site.val();
					var siteArr = siteVal.split(',');
					var taskTypeVal=taskType.val();
					temCover.show();
					temPop.show();
					$("#tempory-task .choose-item li").each(function() {
						if($(this).attr('data') == 1) {
							choose = choose + 1;
						}
					})
					if(siteVal == '' || siteVal == null || siteVal == undefined) {

						temAlert.html('请填写站点名');
						return false;
					} else {
						for(var i = 0; i < siteArr.length; i++) {
							if(!siteArr[i].match(reg)) {
								temAlert.html('填写的网站有误');
								return false;
							}
						}
					}
					if(choose == 0) {
						temAlert.html('请选择检测项');
						return false;
					}
					temTitle.find('.fa').removeClass('fa-warning');
					temTitle.find('.fa').addClass('fa-chevron-circle-down');
					temTitle.find('span').html('下发任务成功');
					console.log(taskType);
					if(taskTypeVal=='周期任务'){
						temAlert.html('下发周期任务成功');
					}else{
						temAlert.html('下发临时任务成功');
					}
			
				});

				//点击确定按钮 弹窗消失
				temBtn.click(function() {
					temCover.hide();
					temPop.hide();
				});
				//传给后台的值
				var json = {

				}

				//下发周期任务的时间选择器
				$(".date-picker").datepicker({
					language: "zh-CN",
					autoclose: true
				});
				$('#example2').DataTable({
					'paging': true,
					'lengthChange': false,
					'searching': false,
					'ordering': true,
					'info': true,
					'autoWidth': false,
					"sScrollY": 270, //DataTables的高  
					"oLanguage": {
						"sLengthMenu": "每页显示 _MENU_ 条记录",
						"sZeroRecords": "对不起，查询不到任何相关数据",
						"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_条记录",
						"sInfoEmtpy": "找不到相关数据",
						"sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
						"sProcessing": "正在加载中...",
						"sSearch": "搜索",
						"oPaginate": {
							"sFirst": "第一页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							"sLast": " 最后一页 "
						},
					}
				});
				$('#result-list').DataTable({
					'paging': true,
					'lengthChange': false,
					'searching': false,
					'ordering': true,
					'info': true,
					'autoWidth': false,
					"oLanguage": {
						"sLengthMenu": "每页显示 _MENU_ 条记录",
						"sZeroRecords": "对不起，查询不到任何相关数据",
						"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_条记录",
						"sInfoEmtpy": "找不到相关数据",
						"sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
						"sProcessing": "正在加载中...",
						"sSearch": "搜索",
						"oPaginate": {
							"sFirst": "第一页",
							"sPrevious": " 上一页 ",
							"sNext": " 下一页 ",
							"sLast": " 最后一页 "
						},
					}
				});
				//结果报告的饼图
//				function resultChart() {
//					var myChart = echarts.init(document.getElementById('result-chart'));
//
//					var option = {
//						tooltip: {
//							trigger: 'item',
//							formatter: "{a} <br/>{b} : {c} ({d}%)"
//						},
//						series: [{
//							name: '库存情况',
//							type: 'pie',
//							radius: '68%',
//							center: ['50%', '50%'],
//							clockwise: false,
//							data: [{
//								value: 45,
//								name: '高危'
//							}, {
//								value: 25,
//								name: '中危'
//							}, {
//								value: 15,
//								name: '低危'
//							}, {
//								value: 8,
//								name: '信息'
//							}],
//							label: {
//								normal: {
//									textStyle: {
//										color: '#000',
//										fontSize: 14,
//									}
//								}
//							},
//							labelLine: {
//								normal: {
//									show: true,
//									color: '#000'
//								}
//							},
//							itemStyle: {
//								normal: {
//									borderWidth: 4,
//									borderColor: '#ffffff',
//								},
//								emphasis: {
//									borderWidth: 0,
//									shadowBlur: 10,
//									shadowOffsetX: 0,
//									shadowColor: 'rgba(0, 0, 0, 0.5)'
//								}
//							}
//						}],
//						color: [
//							'#00acee',
//							'#52cdd5',
//							'#79d9f1',
//							'#a7e7ff',
//							'#c8efff'
//						],
//						backgroundColor: '#fff'
//					};
//					myChart.setOption(option);
//				}
//				resultChart();
			});

			//创建有操作按钮的dataTable
//			$("#tableFour").DataTable({
//
//			});
//			
			//网站风险分布
			function checkSite() {
				var myChart = echarts.init(document.getElementById('check-site-chart'));

				var option = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					series: [{
						name: '库存情况',
						type: 'pie',
						radius: '68%',
						center: ['50%', '50%'],
						clockwise: false,
						data: [{
							value: 45,
							name: '高危'
						}, {
							value: 25,
							name: '中危'
						}, {
							value: 15,
							name: '低危'
						}, {
							value: 8,
							name: '信息'
						}],
						label: {
							normal: {
								textStyle: {
									color: '#000',
									fontSize: 14,
								}
							}
						},
						labelLine: {
							normal: {
								show: true,
								color: '#000'
							}
						},
						itemStyle: {
							normal: {
								borderWidth: 4,
								borderColor: '#ffffff',
							},
							emphasis: {
								borderWidth: 0,
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}],
					color: [
						'#00acee',
						'#52cdd5',
						'#79d9f1',
						'#a7e7ff',
						'#c8efff'
					],
					backgroundColor: '#fff'
				};
				myChart.setOption(option);
			}

//			checkSite();//针对检测项展示的高危，中危，低危
			function bugTypeChart2() {
				var data = [{
						name: '暗链',
						count1: 100,
						count2: 120,
						count3: 50,
						count4: 80,
					},
					{
						name: '挂马',
						count1: 100,
						count2: 70,
						count3: 150,
						count4: 80,
					},
					{
						name: '关键词',
						count1: 100,
						count2: 120,
						count3: 150,
						count4: 80,
					},
					{
						name: 'sql注入漏洞',
						count1: 100,
						count2: 12,
						count3: 150,
						count4: 80,
					},
					{
						name: 'XSS跨站脚本漏洞',
						count1: 100,
						count2: 120,
						count3: 150,
						count4: 70,
					},
					{
						name: '应用漏洞',
						count1: 100,
						count2: 120,
						count3: 90,
						count4: 80,
					},
					{
						name: '信息泄露',
						count1: 100,
						count2: 120,
						count3: 150,
						count4: 80,
					},
					{
						name: 'CGI漏洞',
						count1: 120,
						count2: 120,
						count3: 150,
						count4: 80,
					},
					{
						name: 'CSRF跨站请求伪造漏洞',
						count1: 100,
						count2: 120,
						count3: 50,
						count4: 80,
					},
					{
						name: '表单破解漏洞',
						count1: 100,
						count2: 80,
						count3: 150,
						count4: 80,
					},
				]
				var names = data.map(v => {
					return v.name
				});
				var count1 = data.map(v => {
					return v.count1
				});
				var count2 = data.map(v => {
					return v.count2
				});
				var count3 = data.map(v => {
					return v.count3
				});
				var count4 = data.map(v => {
					return v.count4
				});
				var myChart = echarts.init(document.getElementById('bug-type-chart2'));
				option = {
					title: {
						text: '漏洞类型风险分布图'
					},
					backgroundColor: '#fff',
					color: ['#FF9E67', '#FFC376', '#FFD6B7', '#cccccc'],
					barWidth: 50,
					tooltip: {
						trigger: 'axis',
						axisPointer: { // 坐标轴指示器，坐标轴触发有效
							type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						},
						backgroundColor: '#fff',
						extraCssText: 'box-shadow: 0px 0px 8px 0px rgba(0,0,0,0.2);',
						textStyle: {
							color: "#333",
						}
					},
					grid: {
						left: '5%',
						right: '5%',
						bottom: '0%',
						top: '15%',
						containLabel: true
					},
					legend: {
						align: 'right',
						position: 'right',
						right: 0,
						top: 30,
					},
					xAxis: {
						type: 'category',
						axisLabel: {
							//          formatter: function(name) {
							////              return echarts.format.truncateText(name, 60, '10px Microsoft Yahei', '…');
							//          }
							rotate: 35,
						},
						data: names
					},
					yAxis: {
						type: 'value',
						splitLine: {
							show: true,
							lineStyle: {
								color: ['#f2f2f2']
							}
						},
					},
					series: [{
							type: 'bar',
							name: '高危',
							stack: '总量',
							data: count1
						},
						{
							type: 'bar',
							name: '中危',
							stack: '总量',
							data: count2
						},
						{
							type: 'bar',
							name: '低危',
							stack: '总量',
							data: count3
						},
						{
							type: 'bar',
							name: '信息',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'top',
									formatter: '{c}',
								}
							},
							data: count4
						}
					]
				};
				myChart.setOption(option);

			}
			bugTypeChart2();//网站漏洞类型占比
			function bugTypeChart3() {
				var myChart = echarts.init(document.getElementById('bug-type-chart3'));
				var colorList = [
					'#66c5d7', '#11c88c', '#989cff', '#ffa55d', '#9c7de1', '#5d9eff', '#ffdb5d', '#ee82ed', '#8fca5f', '#b995f5'
				];

				// 总和
				var total = {
					value: '24,652',
					name: '共检测漏洞数'
				}

				var originalData = [{
					value: 55,
					name: '暗链'
				}, {
					value: 70,
					name: '挂马'
				}, {
					value: 25,
					name: "关键词"
				}, {
					value: 25,
					name: "SQL注入漏洞"
				}, {
					value: 25,
					name: "XSS跨站脚本漏洞"
				}, {
					value: 25,
					name: "应用漏洞"
				}, {
					value: 25,
					name: "信息泄露"
				}, {
					value: 25,
					name: "CGI漏洞"
				}, {
					value: 25,
					name: "CSRF跨站请求伪造漏洞"
				}, {
					value: 25,
					name: "表单破解漏洞"
				}];

				echarts.util.each(originalData, function(item, index) {
					item.itemStyle = {
						normal: {
							color: colorList[index]
						}
					};
				});

				var option = {
					tooltip: {
						trigger: 'item',
						formatter: '{b}: {c} ({d}%)'
					},
					backgroundColor: '#ffffff',
					legend: {
						show: true,
						left: 0,
						bottom: 0,
					},
					grid: {
						bottom: 0
					},
					title: [{
						text: total.name,
						left: '49%',
						top: '35%',
						textAlign: 'center',
						textBaseline: 'middle',
						textStyle: {
							color: '#999',
							fontWeight: 'normal',
							fontSize: 18
						}
					}, {
						text: total.value,
						left: '49%',
						top: '43%',
						textAlign: 'center',
						textBaseline: 'middle',
						textStyle: {
							color: '#666',
							fontWeight: 'normal',
							fontSize: 30
						}
					}],
					series: [{
						hoverAnimation: false, //设置饼图默认的展开样式
						center: ['50%', '40%'],
						radius: [100, 120],
						name: 'pie',
						type: 'pie',
						selectedMode: 'single',
						selectedOffset: 16, //选中是扇区偏移量
						clockwise: true,
						startAngle: 90,
						label: {
							normal: {
								show: false
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						itemStyle: {
							normal: {
								borderWidth: 3,
								borderColor: '#ffffff',
							},
							emphasis: {
								borderWidth: 0,
								shadowBlur: 5,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.2)'
							}
						},
						data: originalData
					}]
				};
				myChart.setOption(option);
			}
			bugTypeChart3();//网站风险饼图
			function siteFaqChart(){
				var myChart = echarts.init(document.getElementById('site-faq-chart'));

					var option = {
						tooltip: {
							trigger: 'item',
							formatter: "{a} <br/>{b} : {c} ({d}%)"
						},
						grid:{
							left:'5%',
							 containLabel: true
						},
						series: [{
							name: '库存情况',
							type: 'pie',
							radius: '52%',
							center: ['50%', '50%'],
							clockwise: false,
							data: [{
								value: 45,
								name: '高危'
							}, {
								value: 25,
								name: '中危'
							}, {
								value: 15,
								name: '低危'
							}, {
								value: 8,
								name: '信息'
							}],
							label: {
								normal: {
									textStyle: {
										color: '#000',
										fontSize: 14,
									},
								}
							},
							labelLine: {
								normal: {
									show: true,
									color: '#000'
								}
							},
							itemStyle: {
								normal: {
									borderWidth: 4,
									borderColor: '#ffffff',
								},
								emphasis: {
									borderWidth: 0,
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}],
						color: [
							'#00acee',
							'#52cdd5',
							'#79d9f1',
							'#a7e7ff',
							'#c8efff'
						],
						backgroundColor: '#fff'
					};
					myChart.setOption(option);
			}
			siteFaqChart();
			//漏洞风险分布
			function bugFaqChart() {

				var myChart = echarts.init(document.getElementById('bug-faq-chart2'));
				//ehcarts重构

				var colorList = [
					'#66c5d7', '#11c88c', '#989cff'
				];

				// 总和
				var total = {
					value: '24,652',
					name: '共检测漏洞数'
				}

				var originalData = [{
					value: 55,
					name: '高危'
				}, {
					value: 70,
					name: '中危'
				}, {
					value: 25,
					name: "低危"
				}];

				echarts.util.each(originalData, function(item, index) {
					item.itemStyle = {
						normal: {
							color: colorList[index]
						}
					};
				});

				var option = {
					tooltip: {
						trigger: 'item',
						formatter: '{b}: {c} ({d}%)'
					},
					backgroundColor: '#ffffff',
					legend: {
						show: true,
						left: 0,
						bottom: '5%',
					},
					grid: {
						bottom: 0
					},
					title: [{
						text: total.name,
						left: '49%',
						top: '43%',
						textAlign: 'center',
						textBaseline: 'middle',
						textStyle: {
							color: '#999',
							fontWeight: 'normal',
							fontSize: 18
						}
					}, {
						text: total.value,
						left: '49%',
						top: '52%',
						textAlign: 'center',
						textBaseline: 'middle',
						textStyle: {
							color: '#666',
							fontWeight: 'normal',
							fontSize: 30
						}
					}],
					series: [{
						hoverAnimation: false, //设置饼图默认的展开样式
						center: ['50%', '50%'],
						radius: [80, 100],
						name: 'pie',
						type: 'pie',
						selectedMode: 'single',
						selectedOffset: 16, //选中是扇区偏移量
						clockwise: true,
						startAngle: 90,
						label: {
							normal: {
								show: false
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						itemStyle: {
							normal: {
								borderWidth: 3,
								borderColor: '#ffffff',
							},
							emphasis: {
								borderWidth: 0,
								shadowBlur: 5,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.2)'
							}
						},
						data: originalData
					}]
				};
				myChart.setOption(option);

			}
			bugFaqChart();//漏洞类型风险分布图的图2
			function bugTypeChart4() {
				var bugTypeChart4 = echarts.init(document.getElementById('bug-type-chart4'));
				var option = '';
				option = {
					title: {
						text: 'Awesome Chart'
					},
					xAxis: {
						data: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
					},
					yAxis: {},
					series: [{
						type: 'line',
						data: [220, 182, 191, 234, 290, 330, 310]
					}]
				};

				option = {
					tooltip: {
						trigger: 'axis',
						axisPointer: { // 坐标轴指示器，坐标轴触发有效
							type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					title: {
						text: ''
					},
					legend: {
						data: ['暗链', '挂马', '关键词', 'sql注入漏洞', 'XSS跨站脚本漏洞', '应用漏洞', 'CGI漏洞', 'CSRF跨站请求伪造漏洞', '表单破解漏洞']
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis: {
						type: 'value'
					},
					yAxis: {
						type: 'category',
						data: ['高危', '中危', '低危']
					},
					series: [{
							name: '暗链',
							type: 'bar',
							barWidth: 30,
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [320, 302, 301]
						},
						{
							name: '挂马',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [120, 132, 101]
						},
						{
							name: '关键词',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [220, 182, 191]
						},
						{
							name: 'sql注入漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [150, 212, 201]
						},
						{
							name: 'XSS跨站脚本漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901]
						},
						{
							name: '应用漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901]
						},
						{
							name: 'CGI漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901]
						},
						{
							name: 'CSRF跨站请求伪造漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901]
						},
						{
							name: '表单破解漏洞',
							type: 'bar',
							stack: '总量',
							label: {
								normal: {
									show: true,
									position: 'insideRight'
								}
							},
							data: [820, 832, 901]
						}
					]
				};
				bugTypeChart4.setOption(option);
			}
			bugTypeChart4();
		</script>
	</body>
</html>
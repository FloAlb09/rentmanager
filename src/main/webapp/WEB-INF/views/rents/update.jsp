<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>R&eacuteservations</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">
							<!-- form start -->
							<form class="form-horizontal" method="post"
								action="/rentmanager/rents/update">
								<!--action="/rents/create"-->
								<div class="box-body">
									<div class="form-group">
										<label for="car" class="col-sm-2 control-label">Voiture</label>

										<div class="col-sm-10">
											<select class="form-control" id="vehicle" name="vehicleU">
												<c:forEach items="${listVehiclesR}" var="vehicle">
													<option value="${vehicle.id}">${vehicle.constructeur}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="client" class="col-sm-2 control-label">Client</label>

										<div class="col-sm-10">
											<select class="form-control" id="client" name="clientU">
												<c:forEach items="${listClientsR}" var="client">
													<option value="${client.id}">${client.prenom}
														${client.nom}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="begin" class="col-sm-2 control-label">Date de d&eacutebut</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="debut"
												name="debutU" value="${debutU}" placeholder="dd/mm/yyyy"
												data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
										</div>
									</div>
									<div class="form-group">
										<label for="fin" class="col-sm-2 control-label">Date de fin</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="fin" name="finU" placeholder="dd/mm/yyyy"
												value="${finU}" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
										</div>
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="submit" name="action" value="1"
										class="btn btn-info pull-right">Modifier</button>
									<button type="submit" name="action" value=""
										class="btn btn-danger pull-right">Annuler</button>
								</div>
								<!-- /.box-footer -->
							</form>
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>

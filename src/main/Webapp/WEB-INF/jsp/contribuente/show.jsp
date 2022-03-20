<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.nome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.cognome}</dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.codiceFiscale}</dd>
					</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di nascita:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_contribuente_attr.dataDiNascita}" /></dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Indirizzo:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.indirizzo}</dd>
					</dl>
					
					<dl class="row">
					  <dt class="col-sm-3 text-right">Totale importo cartelle:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.totaleImportoCartelle()}</dd>
					</dl>
					
					<dl class="row">
					  <dt class="col-sm-3 text-right">Totale concluso e pagato:</dt>
					  <dd class="col-sm-9">${show_contribuente_attr.totaleConclusoEPagato()}</dd>
					</dl>
					
					<c:if test="${show_contribuente_attr.isInContenzioso()}">
						<dl class="row">
						  <dt class="col-sm-3 text-right text-danger">Totale in contenzioso:</dt>
						  <dd class="col-sm-9 text-danger">${show_contribuente_attr.totaleInContenzioso()}</dd>
						</dl>
			    	</c:if>
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>
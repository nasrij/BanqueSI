var app=angular.module("myBanqueApp",[]);

app.controller("myBanqueController",function($scope,$http){
	$scope.compte = null;
	$scope.operation={type:"versement",montant:0};
	$scope.codeCompte = null;
	$scope.pageOperations = [];
	$scope.pageCourante=0;
	$scope.pageSize=3;
	$scope.pages=[];
	$scope.operation.cpte1 = null;
	$scope.errorMessage = null;
	$scope.errorMessage1 = null;

	$scope.chargerCompte=function(){
		$scope.errorMessage1 = null;
		$scope.compte = null;
		$scope.pageCourante=0;
		$http.get("/comptes/"+$scope.codeCompte).success(function(data){
			$scope.compte = data;				
			$scope.chargerOperations();
		}).error(function(data) {			
			$scope.errorMessage1 = data.message;
		})	};
	
	$scope.goToPage=function(p){
		$scope.pageCourante = p;
		$scope.chargerOperations();
	}
	

$scope.chargerOperations=function(){
	$scope.errorMessage = null;
	$http.get("/operations?codeCompte="+$scope.codeCompte+"&page="+$scope.pageCourante+"&size="+$scope.pageSize).success(function(data){
		$scope.pageOperations = data;
		$scope.pages= new Array(data.totalpages)
	});
}
$scope.saveOperation=function(){
	var params = "";
	if($scope.operation.type == 'virement')
		{
			params = "cpte1="+$scope.codeCompte+"&cpte2="+$scope.operation.cpte1;
		}else{
			params = "code="+$scope.codeCompte;
		}
	
	
	$http({
		method: "PUT",
		url: $scope.operation.type,
		data: params+"&montant="+$scope.operation.montant+"&codeEmp=5",
		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	}).success(function(data) {
		$scope.chargerCompte();			
	}).error(function(data) {
		$scope.errorMessage = data.message;
		
	})
};

});
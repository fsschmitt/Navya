// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or vendor/assets/javascripts of plugins, if any, can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// the compiled file.
//
// WARNING: THE FIRST BLANK LINE MARKS THE END OF WHAT'S TO BE PROCESSED, ANY BLANK LINE SHOULD
// GO AFTER THE REQUIRES BELOW.
//
//= require jquery
//= require jquery_ujs
//= require foundation
//= require_tree .

$(document).ready(function() {
  $(".news-content").hide();

$(".news-title").click(function() {
	$(".news-content").hide({direction: "up"});
	var id = "#"+$(this).attr("id")+"-content"
	$(id).show({direction: "down"});
});

});

var id;

$(function() {
 	
 	$('.report').click(function() {
 		id = $(this).attr('id');
		$('#myModal').reveal();
		$('.close-reveal-modal').css("position", "");
	});




});

function save() {
	var op = $('#standardDropdown').find(":selected").text();

	if(op == "Economia e Política")
		changeCat(1)
	else if(op == "Desporto")
		changeCat(2);
	else if(op == "Cultura e Lazer")
		changeCat(3);
	else if(op == "Ciência e Tecnologia")
		changeCat(4);
	else
		changeCat(5);

}

function changeCat(newcat){
	console.log(id);
	console.log(newcat);
	$.ajax({
	type: "GET",
	url: "/home/addToTrainingSet?id=" + id + "&cat=" + newcat
	});
}
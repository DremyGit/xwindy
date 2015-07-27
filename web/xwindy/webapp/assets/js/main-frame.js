$("#nav-search").click(function () {
    $("#div-search").toggle();
})
$('#input-search').bind('keypress',function(event){
    if(event.keyCode == "13")
    {
        search();
    }
});

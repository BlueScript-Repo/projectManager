
  function hideOthers(idToBeOpen)
  {

    var idVals = ["item1","accessory1","valve1"];

    for(var i=0; i< idVals.length; i++)
    {
      if(idToBeOpen !== idVals[i])
      {
        $('#'+idVals[i]).collapse("hide");

      }
      else
      {
        if($('#'+idToBeOpen).hasClass('collapse'))
        {
          $('#'+idToBeOpen).collapse("show");  
        }
        else
        {
         $('#'+idToBeOpen).collapse("hide"); 
       }

     }

   }
 }


  function hideOthers1(idToBeOpen)
  {

    var idVals = ["aitem","citem"];

    for(var i=0; i< idVals.length; i++)
    {
      if(idToBeOpen !== idVals[i])
      {
        $('#'+idVals[i]).collapse("hide");

      }
      else
      {
        if($('#'+idToBeOpen).hasClass('collapse'))
        {
          $('#'+idToBeOpen).collapse("show");  
        }
        else
        {
         $('#'+idToBeOpen).collapse("hide"); 
       }

     }

   }
 }



  function showLoading()
  {
    $.blockUI({ css: { 
      border: 'none', 
      padding: '15px', 
      backgroundColor: '#fff', 
      '-webkit-border-radius': '10px', 
      '-moz-border-radius': '10px', 
      opacity: .5, 
      color: '#000',
      images : 'images/img/loading-blue.gif'
    } }); 

  }

  function hideLoading()
  {
    /*this.setTimeout($.unblockUI,1000);*/
    $.unblockUI();

  }



  function captureFileLocation() {
    $('#LoadingImage').show();
    var fileLocation;
    var entrerLocationRequest = prompt("Please Enter the file location", "BOQ File Location");
    if (entrerLocationRequest == null || entrerLocationRequest == "") {
      fileLocation = "User cancelled the prompt.";
    } else {
      fileLocation = entrerLocationRequest;
    }
    console.log("File Location : "+fileLocation);

    if(fileLocation != "User cancelled the prompt.")
    {
      showLoading();
      $.ajax({
       type : 'POST',
       data : {'location' : fileLocation},
       url : 'import',
       success : function(data) {

        $('#tableContentDetails').html(data);
        adjustWidth();
        hideLoading();
      }
    });

      var imp = document.getElementById("importBoq");
        //imp.style.display = "none";
        
        var generate = document.getElementById("generate");
        generate.style.display = "block";
        
        var generateInq = document.getElementById("generateQuot");
        generateInq.style.display = "block";
      }
    }
    function adjustWidth() 
    {
     console.log('Inside adjustWidth');
   }




  function myFunction(value, tagName, nextTagName) {

    $('#LoadingImage').show();
    var tag = '#'+nextTagName;
    var inventory = $('#inventoryName')[0].value;
    
    showLoading();

    $.ajax({
     type : 'POST',
     data :  {'value' : value,'currentTag' : tagName,'nextTagName' : nextTagName, 'inventory' : inventory},
     url : 'getDropdown',
     success : function(data) {

      console.log(data);
      console.log($(tag));

      var blank = "<option> </option>";
      $(tag).html(blank);
      $(tag).append(data);
      hideLoading();
    }
  });
  }



  function updateSupplyRate(thisObj)
  {

    var rate = [];
    var quantity = [];

    var obj = $(thisObj);

    console.log($(thisObj).find('parentElement').find('parentElement').find('parentElement'))
    console.log("Base count is :  "+$(thisObj).find('#inventoryDetails').find("input[name='baseSupplyRate']").length);

    var thisBaseSupplyRates = $($(obj).closest("table")[0]).find("input[name='baseSupplyRate']");
    /*$("input[name='baseSupplyRate']").each(function() {*/
      $(thisBaseSupplyRates).each(function() {
        var sRate = parseFloat($(this).val()) + parseFloat($("[name='supplyPrsnt']").val()*$(this).val()/100);

        if(sRate % 5 != 0)
        {
          sRate + 5 - sRate % 5
        }
        rate.push(sRate);
      });

      var thisSupplyRates = $($(obj).closest("table")[0]).find("input[name='supplyRate']");
      var int = 0;
      $(thisSupplyRates).each(function() {
        $(this).val(rate[int]);
        int++;
      });

      var thisQuantity = $($(obj).closest("table")[0]).find("input[name='quantity']");
      $(thisQuantity).each(function() {
       quantity.push($(this).val());
     });


      var thisSupplyAmount = $($(obj).closest("table")[0]).find("input[name='supplyAmount']");

      var i = 0;
      $(thisSupplyAmount).each(function() {
       console.log(rate[i]);
       console.log(quantity[i]);
       $(this).val(rate[i]*quantity[i]);
       i++;
     });

      updateSubTotal($('.createBOQ').find('.active.show').attr('href').substr(1));

    }



    function updateErectionRate(thisObj)
    {

      var rate = [];
      var quantity = [];

      var obj = $(thisObj);

      var thisBaseErectionRate = $($(obj).closest("table")[0]).find("input[name='baseErectionRate']");

      $(thisBaseErectionRate).each(function() {
        var eRate = parseFloat($(this).val()) + parseFloat($("[name='erectionPrsnt']").val()*$(this).val()/100);
        rate.push(eRate);
      });

      var thisErectionRate = $($(obj).closest("table")[0]).find("input[name='erectionRate']");
      var int = 0;
      $(thisErectionRate).each(function() {
        $(this).val(rate[int]);
        int++;
      });

      var thisQuantity = $($(obj).closest("table")[0]).find("input[name='quantity']");
      $(thisQuantity).each(function() {
       quantity.push($(this).val());
     });

      var thisErectionAmount = $($(obj).closest("table")[0]).find("input[name='erectionAmount']");

      var i = 0;
      $(thisErectionAmount).each(function() {
       console.log(rate[i]);
       console.log(quantity[i]);
       $(this).val(rate[i]*quantity[i]);
       i++;
     });


      updateSubTotal($('.createBOQ').find('.active.show').attr('href').substr(1));
    }



    function updateSubTotal(sheetName)
    {

      var supSubTotal = 0;
      for(var i=0; i < $('#'+sheetName).find('[name="supplyAmount"]').length;i++)
      {
        supSubTotal += parseFloat($('#'+sheetName).find('[name="supplyAmount"]')[i].value);
      }

      var ereSubTotal = 0;
      for(var i=0; i < $('#'+sheetName).find('[name="erectionAmount"]').length;i++)
      {
        ereSubTotal += parseFloat($('#'+sheetName).find('[name="erectionAmount"]')[i].value);
      }

      $('#'+sheetName+'SupSubTotal').text(supSubTotal);
      $('#'+sheetName+'EreSubTotal').text(ereSubTotal);

    }




    $(document).on('click', 'ul.nav-tabs a', function (e) {
      e.preventDefault();  
      $(this).tab('show');
    });




    $(document).ready(function(){
   // we define and invoke a function
   (function(){

     var inputArray = $("input[name='boqNameList']")[0].value.split(",");
     
     var names = [];
     $.each(inputArray, function(i, el){
      if($.inArray(el, names) === -1) 
      {
       names.push(el);
     }
   });

     var dummy = "<option value=\"BOQRevisions\"><h5>BOQRevisions</h5></option>";
     
     $.each(names,function(i){

      var dummy1 = dummy.replace("BOQRevisions",names[i]);

      var tags = dummy1.replace("BOQRevisions",names[i]);

      $('.revisionSection').append(tags);

    });
     
   })();
 });

    $(document).ready(function(){
   // we define and invoke a function
   (function(){

     var inputArray = $("input[name='quotationNamesList']")[0].value.split(",");
     
     var names = [];
     $.each(inputArray, function(i, el){
      if($.inArray(el, names) === -1) 
      {
        names.push(el);
      }
    });

     var dummy = "<option value=\"QuotationRevisions\"><h5>QuotationRevisions</h5></option>";
     
     $.each(names,function(i){

      var dummy1 = dummy.replace("QuotationRevisions",names[i]);

      var tags = dummy1.replace("QuotationRevisions",names[i]);

      $('.offerRevisionSection').append(tags);

    });
     
   })();
 });

    $(document).ready(function(){
   // we define and invoke a function
   (function(){

     var inputArray = $("input[name='taxInvoiceNamesList']")[0].value.split(",");
     
     var names = [];
     $.each(inputArray, function(i, el){
      if($.inArray(el, names) === -1) 
      {
        names.push(el);
      }
    });

     var dummy = "<option value=\"taxInvoiceName\"><h5>taxInvoiceName</h5></option>";
     
     $.each(names,function(i){

      if(names[i]!=='')
      {
        var dummy1 = dummy.replace("taxInvoiceName",names[i]);
        var tags = dummy1.replace("taxInvoiceName",names[i]);

        $('#taxInvoiceList').append(tags);
        $('#taxInvoiceNumber').append(tags);
      }
    });
     
   })();
 });

    $(document).ready(function(){
   // we define and invoke a function
   (function(){

     var inputArray = $("input[name='poNamesList']")[0].value.split(",");
     
     var names = [];
     $.each(inputArray, function(i, el){
      if($.inArray(el, names) === -1) 
      {
        names.push(el);
      }
    });

     var dummy = "<option value=\"poName\"><h5>poName</h5></option>";
     
     $.each(names,function(i){

      if(names[i]!=='')
      {
        var dummy1 = dummy.replace("poName",names[i]);
        var tags = dummy1.replace("poName",names[i]);

        $('#poList').append(tags);
      }

    });
     
   })();
 });




  function download(name, sectionName) 
  {

    showLoading();
    
    if(sectionName === 'tableContentInqSec')
    {
      $('.tableContentInqSec').css('display','block');
    }
    else if(sectionName === 'tableContentPOSec')
    {
      $('.tableContentPOSec').css('display','block');
    }
    else
    {
      $('.createBOQ').show();
      $('.generateBOQButton').css('display','block');
      $('.inventoryTableHeader').css('display','block');  
    }    
    
    $('[name="boqName"]')[0].value = name.substring(0, name.length-3);
    
    var projectId = $('[name="projectId"]')[0].value;  

    var formData = $(this).serializeArray();

    formData.push({name: 'projectId', value: projectId});
    formData.push({name: 'boqName', value: name});


    $.ajax({
     type : 'GET',
     data :  formData,
     url : 'downloadBoq',
     success : function(data)
     { 

       var details = data.split('::');

       var headerDetails = details[0];

       var headerHTML = $.parseHTML(headerDetails);

       for(var k=0;k<headerHTML.length-1;k++)
       {
        var ele = headerHTML[k];
        var val = headerHTML[k].value;

        $('[name="'+ele.name+'"]').attr('value',val);
      }

      var sheetDetailsHTML = headerHTML[headerHTML.length-1];
      $('[name="generateBOQ"]').append(sheetDetailsHTML);

      $('#sheetList'+sectionName).html('');

      var isFirst = true;
      for(var i=1;i<details.length-1;i++)
      {
        var sheetName = details[i];
        var className = '';

        if(isFirst)
        {
          className = 'active show';
        } 

        $('#sheetList'+sectionName).append('<li class="nav-item"><a class="nav-link '+sheetName+' '+className+'" href="#'+sheetName+'" role="tab" data-toggle="tab" aria-selected="true">'+sheetName+'<i class="fa fa-times pr-2" onClick="removeSheet('+sheetName+');" style="margin-left:5px;"></i></a></li>');

        isFirst = false;
      }

      var tagData = details[details.length-1]; 
      $('#'+sectionName+'').html(tagData);
      $('#LoadingImage').hide();

    }

  });

    hideLoading();
  }
  function adjustWidthDn() 
  {
   console.log('Inside adjustWidth');
 }





  function createInquiry() 
  {

    showLoading();

    var inventoryName       = [];
    var material            = [];
    var type                = [];
    var manifacturingMethod = [];
    var classOrGrade        = [];
    var ends                = [];
    var size                = [];
    var quantity            = [];
    var baseSupplyRate      = [];
    var supplyRate          = [];
    var baseErectionRate    = [];
    var erectionRate        = [];
    var supplyAmount        = [];
    var erectionAmount      = [];
    var sheetDetails        = "";

    var tabs = $('.tab-pane');
    for(var r=0;r<tabs.length;r++)
    {

      var CheckeleCount = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input:checkbox').length;

      var selectedElements = [];
      var i;
      var checkedEle = 0;
      for(i=0; i < CheckeleCount; i++)
      {
        if($($('#tableContentInqSec').find('.tab-pane')[r]).find('input:checkbox')[i].checked)
        {
         selectedElements[i] = i;
         checkedEle++;
       }
     }

     if(checkedEle>0)
     {
      sheetDetails = sheetDetails + $($('#tableContentInqSec').find('.tab-pane')[r]).attr('id')+','+checkedEle+',';
    }

    var eleCount = $('#tableContentInqSec input').length;
    var j;
    var k = 0;
    var n = 1;
    for(k=0;k<selectedElements.length;k++)
    {

     if(selectedElements[k] != undefined)
     {
      var start = 3 + 15*parseFloat(selectedElements[k]);
      console.log('Start is : '+start);

      quantity[quantity.length+k]           = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      baseSupplyRate[baseSupplyRate.length+k]     = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      supplyRate[supplyRate.length+k]         = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      baseErectionRate[baseErectionRate.length+k]   = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      erectionRate[erectionRate.length+k]       = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      supplyAmount[supplyAmount.length+k]       = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      erectionAmount[erectionAmount.length+k]     = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      inventoryName[inventoryName.length+k]       = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;                    
      material[material.length+k]           = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;     
      type[type.length+k]               = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;

      var tempVal = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;

      if(tempVal==="")
      {
        manifacturingMethod[manifacturingMethod.length+k] = " ";
      }
      else
      {
        manifacturingMethod[manifacturingMethod.length+k] = tempVal;
      }


      classOrGrade[classOrGrade.length+k]       = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      ends[ends.length+k]               = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
      size[size.length+k]               = $($('#tableContentInqSec').find('.tab-pane')[r]).find('input')[start++].value;
    } 
  }
}
var lastArray = 3 + 20*parseFloat(selectedElements[parseFloat(selectedElements.length) - 1]);


var formData = $(this).serializeArray();

formData.push({name: 'projectId', value: $('[name="projectId"]').val()});
formData.push({name: 'boqName', value: $('[name="inquiryName"]').val()});


var inventoryName_string = cleanArray(inventoryName);
formData.push({name: 'inventoryName', value: inventoryName_string});
var material_string = cleanArray(material);
formData.push({name: 'material', value: material_string});
var type_string = cleanArray(type);
formData.push({name: 'type', value: type_string});
var manifacturingMethod_string = cleanArray(manifacturingMethod);
formData.push({name: 'manifMetod', value: manifacturingMethod_string});
var classOrGrade_string = cleanArray(classOrGrade);
formData.push({name: 'classOrGrade', value: classOrGrade_string});
var ends_string = cleanArray(ends);
formData.push({name: 'ends', value: ends_string});
var size_string = cleanArray(size);
formData.push({name: 'size', value: size_string});
var quantity_string = cleanArray(quantity);
formData.push({name: 'quantity', value: quantity_string});

var baseSupplyRate_string = cleanArray(baseSupplyRate);
formData.push({name: 'baseSupplyRate', value: baseSupplyRate_string});
var supplyRate_string = cleanArray(supplyRate);
formData.push({name: 'supplyRate', value: supplyRate_string});
var baseErectionRate_string = cleanArray(baseErectionRate);
formData.push({name: 'baseErectionRate', value: baseErectionRate_string});
var erectionRate_string = cleanArray(erectionRate);
formData.push({name: 'erectionRate', value: erectionRate_string});

var supplyAmount_string = cleanArray(supplyAmount);
formData.push({name: 'supplyAmount', value: supplyAmount_string});
var erectionAmount_string = cleanArray(erectionAmount);
formData.push({name: 'erectionAmount', value: erectionAmount_string});

formData.push({name: 'venderName', value: $('[name="selectedVenderName"]').val()});
formData.push({name: 'isOffer', value: 'true'});
formData.push({name: 'sheetDetails', value: sheetDetails});

var inquiryNameList = $('.offerRevisionSection')[0].options; 

var revisionNo = 0;
for(var k = 0; k < inquiryNameList.length; k++)
{
  if(inquiryNameList[k].value.startsWith('Inquiry_'+$('[name="inquiryName"]').val()))
  {
    revisionNo ++;
    var optionVal = inquiryNameList[k].value;
    console.log(optionVal);        
  }

}

console.log('revisionNo is : '+revisionNo);    

var inquiryName = '<option value="Inquiry_'+ $('[name="inquiryName"]').val() +'_R'+revisionNo+'">Inquiry_'+$('[name="inquiryName"]').val()+'_R'+revisionNo+'</option>';
$.ajax({
 url: "generate",
 data: formData,
 type: 'post',
 success: function(data) {

  console.log("Appending "+inquiryName);
  $('.offerRevisionSection').append(inquiryName);
  hideLoading();

  alert('Inquiry has been generated successfully. Please check the Notification section to verify and send.');
}
});

}

function cleanArray(actual)
{
  var newArray = new Array();
  for(var i = 0; i<actual.length; i++)
  {
    if (actual[i])
    {
      newArray.push(actual[i]);
    }
  }
  return newArray;
}





  function generatePO()
  { 
    showLoading();
    var length = $('#tableContentPOSec  input').length;

    var line;
    var i;
    for(i=1;i<length;i++)
    {
     if($('#tableContentPOSec  input')[i].name == "inventoryName")
     {
      var temp = $('#tableContentPOSec  input')[i];
      line = $(temp).clone();
    } 
    else if($('#tableContentPOSec  input')[i].name == "material")
    {
      var temp = $('#tableContentPOSec  input')[i];
      line = $(temp).clone();
    }
    else if($('#tableContentPOSec  input')[i].name == "type")
    {
      var temp = $('#tableContentPOSec  input')[i];
      line = $(temp).clone();
    }
    else if($('#tableContentPOSec  input')[i].name == "manifMetod")
    {
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }
    else if($('#tableContentPOSec input')[i].name == "classOrGrade")
    {
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }
    else if ($('#tableContentPOSec input')[i].name == "ends")
    {
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }
    else if ($('#tableContentPOSec input')[i].name == "size")
    {
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }
    else if ($('#tableContentPOSec input')[i].name == "quantity")
    {
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }
    else if ($('#tableContentPOSec input')[i].name == "supplyRate")
    {
      if($('#tableContentPOSec input')[i].value === "")
      {
        alert('Please provide the supply Rates and retry.!!');
        hideLoading();
        return;
      }
      var temp = $('#tableContentPOSec input')[i];
      line = $(temp).clone();
    }

    $('#generateOffer').append($(line));    
  }

  var temp = $('[name="projectId"]')[0];
  line = $(temp).clone();
  $('#generateOffer').append($(line));

  $('#generateOffer').submit();
  showLoading();
}



  function toggleCreateBOQSec()
  {
   $('.createBOQ').toggle();
  }


  function toggleImportSec()
  {
    console.log('calling toggleImportSec');
    $('.importBOQ1').toggle();
    $('.importBOQ2').toggle();
  }    




  function appendInventory() 
  {

    $('.generateBOQButton').css('display','block');
    $('.inventoryTableHeader').css('display','block');

    var inventoryName = $('#inventoryName').children("option:selected").val();
    var material = $('#material').children("option:selected").val();
    var type = $('#type').children("option:selected").val();
    var manifacturingMethod = $('#manifacturingMethod').children("option:selected").val();
    var classOrGrade = $('#classOrGrade').children("option:selected").val();
    var ends = $('#ends').children("option:selected").val();
    var size = $('#size').val();

    console.log(type);

    var template = "<tr>"
    + "    <td> <input type='button' onClick='removeItem($(this));' class='btn' value='&times;' ></td>"
    + "    <td class='align-middle'> <input type='hidden' name='inventoryName' value='"+inventoryName+"'></input>"+inventoryName+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='material' value='"+material+"'></input>"+material+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='type' value='"+type+"'></input>"+type+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='manifMetod' value='"+manifacturingMethod+"'></input>"+manifacturingMethod+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='classOrGrade' value='"+classOrGrade+"'></input>"+classOrGrade+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='ends' value='"+ends+"'></input>"+ends+"</td>"
    + "    <td class='align-middle'> <input type='hidden' name='size' value='"+size+"'></input>"+size+"</td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' name='quantity' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' onchange='updateSupplyRate($(this));' name='baseSupplyRate' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' onchange='updateSupplyRate($(this));' name='supplyRate' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' onchange='updateErectionRate($(this));' name='baseErectionRate' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' onchange='updateErectionRate($(this));' name='erectionRate' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' name='supplyAmount' value=''></input></td>"
    + "    <td><input class='form-control' style='width:60px;' type='text' name='erectionAmount' value=''></input></td>";

    console.log(template);
    $('.inventoryDetails').css("display","block");            

    var sheet = $($('.nav-link.active.show')[1]).attr('href').substring(1);

    if($('#'+sheet).find('tbody#tableContentDetails.inventry').length>0)
    {
        $('#'+sheet).find('tbody#tableContentDetails.inventry').last().after(template);
    }
    else if($('#'+sheet).find('tbody#tableContentDetails.accessoryTr').length>0)
    {
        $('#'+sheet).find('tbody#tableContentDetails.accessoryTr').first().before(template);
    }
    else
    {
     $('#'+sheet).find('tbody#tableContentDetails').append(template);
    }
 }





  function appendAccessory() 
  {

    $('.generateBOQButton').css('display','block');
    $('.inventoryTableHeader').css('display','block');

    var accessoryName = $('#accessoryName').val();
    var desc1         = $('#desc1').val();
    var desc2         = $('#desc2').val();
    var desc3         = $('#desc3').val();
    var desc4         = $('#desc4').val();
    var desc5         = $('#desc5').val();

    console.log(type);

    var template = "<tr class='accessoryTr'>"
    + "    <td> <input type='button' onClick='removeItem($(this));' class='btn' value='&times;'> </td>"
    + "    <td> <input type='hidden' name='inventoryName' value='"+accessoryName+"'></input>"+accessoryName+"</td>"
    + "    <td> <input type='hidden' name='material' value='"+desc1+"'></input>"+desc1+"</td>"
    + "    <td> <input type='hidden' name='type' value='"+desc2+"'></input>"+desc2+"</td>"
    + "    <td> <input type='hidden' name='ends' value='"+desc3+"'></input>"+desc3+"</td>"
    + "    <td> <input type='hidden' name='classOrGrade' value='"+desc4+"'></input>"+desc4+"</td>"
    + "    <td> <input type='hidden' name='manifMetod' value='"+desc5+"'></input>"+desc5+"</td>"
    + "    <td> <input type='hidden' name='size' value='-'></input>-</td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='quantity' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='baseSupplyRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='supplyRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='baseErectionRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='erectionRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='supplyAmount' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='erectionAmount' value=''></input></td>";

    console.log(template);
    $('.inventoryDetails').css("display","block");

    var sheet = $($('.nav-link.active.show')[1]).attr('href').substring(1);

    if($('#'+sheet).find('tbody#tableContentDetails.inventry').length>0)
    {
        $('#'+sheet).find('tbody#tableContentDetails.inventry').last().after(template);
    }
    else if($('#'+sheet).find('tbody#tableContentDetails.accessoryTr').length>0)
    {
        $('#'+sheet).find('tbody#tableContentDetails.accessoryTr').first().before(template);
    }
    else
    {
        $('#'+sheet).find('tbody#tableContentDetails').append(template);
    }
 }




  function appendValve() 
  {

    $('.generateBOQButton').css('display','block');
    $('.inventoryTableHeader').css('display','block');

    var model         = $('#model').val();
    var material        = $('#materialVal').val();
    var end           = $('#endVal').val();
    var type          = $('#typeVal').val();
    var pressureRatings   = $('#pressureRatingsVal').val();
    var maxInletPressure    = $('#maxInletPressureVal').val();
    var operations        = $('#operationsVal').val();     
    var seatAndSeals    = $('#seatAndSealsVal').val();     
    var valveSize       = $('#valveSize').val();    

    var template = "<tr class='accessoryTr'>" 
    + "    <td> <input type='button' onClick='removeItem($(this));' class='btn' value='&times;'> </td>"
    + "    <td> <input type='hidden' name='inventoryName' value='"+model+"'></input>"+model+"</td>"
    + "    <td> <input type='hidden' name='material' value='"+material+"'></input>"+material+"</td>"    
    + "    <td> <input type='hidden' name='type' value='"+type+"-"+operations+"-"+seatAndSeals+"'></input>"+type+"-"+operations+"-"+seatAndSeals+"</td>"
    + "    <td>-</td>"
    + "    <td> <input type='hidden' name='classOrGrade' value='"+pressureRatings+"-"+maxInletPressure+"'></input>"+pressureRatings+"-"+maxInletPressure+"</td>"
    + "    <td> <input type='hidden' name='ends' value='"+end+"'></input>"+end+"</td>"
    + "    <td> <input type='hidden' name='manifMetod' value=''> <input type='hidden' name='size' value='"+valveSize+"'></input>"+valveSize+"</td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='quantity' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='baseSupplyRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='supplyRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='baseErectionRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='erectionRate' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='supplyAmount' value=''></input></td>"
    + "    <td> <input class='form-control' style='width:60px;' type='text' name='erectionAmount' value=''></input></td>";

    console.log(template);
    $('.inventoryDetails').css("display","block");


    var sheet = $($('.nav-link.active.show')[1]).attr('href').substring(1);

    if($('#'+sheet).find('tbody#tableContentDetails.inventry').length>0)
    {
     $('#'+sheet).find('tbody#tableContentDetails.inventry').last().after(template);
   }
   else if($('#'+sheet).find('tbody#tableContentDetails.accessoryTr').length>0)
   {
     $('#'+sheet).find('tbody#tableContentDetails.accessoryTr').first().before(template);
   }
   else
   {
     $('#'+sheet).find('tbody#tableContentDetails').append(template);
   }  
 }





  function downloadInvoice( invoiceName )
  {
   window.location.assign("showInvoice?invoiceName="+invoiceName);
 }

 function downloadPO( poName )
 {
   window.location.assign("showPO?poName="+poName);
 }



  $(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
  });



  function toggleProjSec()
  {
   $('.projDetails').toggle();
 }

 function togglePayDetailsSec()
 {
   $('#payDetailsSection').toggle();
 }



  function updateVal(){
   var promptVal = prompt("Enter %","0%");
   console.log(promptVal);
 }



  $(function() {
    $('button[name=uploadFile]').click(function(e) {
      e.preventDefault();
      showLoading();
    //Disable submit button
    $(this).prop('disabled',true);
    $('.inventoryTableHeader').css('display','block');
    var form = document.forms[name="fileUploadForm"];
    
    var formData = new FormData(form);

    console.log(formData);  
    // Ajax call for file uploaling
    var ajaxReq = $.ajax({
      url : 'import',
      type : 'POST',
      data : formData,
      cache : false,
      contentType : false,
      processData : false,
      
      success: function(data) 
      {
       $('#tableContentDetails').html(data);
       
       toggleCreateBOQSec();
       adjustWidth();

       var imp = document.getElementById("importBoq");
          //imp.style.display = "none";
          
          var generate = document.getElementById("generate");
          generate.style.display = "block";
          
          var generateInq = document.getElementById("generateQuot");
          generateInq.style.display = "block";
          
          $('[name="client"]')[0].value   = $('#iDclient')[0].value;  
          $('[name="site"]')[0].value  = $('#iDsite')[0].value;    
          $('[name="project"]')[0].value   = $('#iDproject')[0].value;   
          $('[name="dName"]')[0].value   = $('#iDdName')[0].value;     
          $('[name="utility"]')[0].value   = $('#iDutility')[0].value;   
          $('[name="pressure"]')[0].value  = $('#iDpressure')[0].value;  
          $('[name="temperature"]')[0].value   = $('#iDtemp')[0].value;    
          $('[name="dNo"]')[0].value     = $('#iDdNo')[0].value;

          $('#LoadingImage').hide();
        }
      });

    // Called on success of file upload
    ajaxReq.done(function(msg) {
      $('input[type=file]').val('');
      $('button[type=submit]').prop('disabled',false);
    });
    
    // Called on failure of file upload
    ajaxReq.fail(function(jqXHR) {
      $('button[type=submit]').prop('disabled',false);
    });
    
    $(this).prop('disabled',false);
  });
  });
  hideLoading();



  $('.statusTo').on('click',function() {

   showLoading();


   var quantity     = $(this.form.elements)[0].value;
   var inventoryStr    = $(this.form.elements)[2].value;
   var materialStr    = $(this.form.elements)[3].value;
   var typeStr      = $(this.form.elements)[4].value;
   var manifMethodStr = $(this.form.elements)[5].value;
   var gradeOrClassStr = $(this.form.elements)[6].value;
   var endsStr      = $(this.form.elements)[7].value;
   var sizeStr      = $(this.form.elements)[8].value + '"';
   var purchaseRateStr = $(this.form.elements)[9].value;
   var projectStr   = $(this.form.elements)[10].value;
   var locationStr    = $(this.form.elements)[11].value;
   var projectId    = $(this.form.elements)[13].value;
   var projectName    = $(this.form.elements)[14].value;
   var projectDesc    = $(this.form.elements)[15].value;
   var statusTo   = $(this).val();

   if(statusTo !== '')
   {
    $.ajax({
     url: "release",
     data: { 'inventoryStr' :   inventoryStr,
     'materialStr' :  materialStr,    
     'typeStr' :    typeStr,      
     'manifMethodStr' :   manifMethodStr, 
     'gradeOrClassStr' : gradeOrClassStr, 
     'endsStr' :    endsStr,      
     'sizeStr' :    sizeStr,      
     'purchaseRateStr' : purchaseRateStr, 
     'projectStr' :     projectStr,   
     'locationStr' :  locationStr,
     'quantity'  :    quantity,   
     'projectId' :    projectId,    
     'projectName' :  projectName,    
     'projectDesc' :  projectDesc,
     'statusTo' :     statusTo},
     type: 'post',
     success: function(data) {
      console.log(data);
      $('#LoadingImage').hide();

    }
  });
  } 

  hideLoading();
});



  $('.accessoryStatusTo').on('change',function() {

    showLoading();


    var quantity      = $(this.form.elements)[0].value;
    var accessoryStatusTo = $(this.form.elements)[1].value;
    var desc1         = $(this.form.elements)[2].value;
    var desc2         = $(this.form.elements)[3].value;
    var desc3       = $(this.form.elements)[4].value;
    var desc4       = $(this.form.elements)[5].value;
    var desc5       = $(this.form.elements)[6].value;
    var accessoryName     = $(this.form.elements)[7].value; 
    var project       = $(this.form.elements)[8].value; 
    var locationStr     = $(this.form.elements)[9].value; 
    var projectId     = $(this.form.elements)[10].value;
    var projectName     = $(this.form.elements)[11].value;
    var projectDesc     = $(this.form.elements)[12].value;

    if(accessoryStatusTo !== '')
    {
      $.ajax({
       url: "releaseAccessory",
       data: { 'quantity'       : quantity,
       'accessoryStatusTo' : accessoryStatusTo,
       'desc1'      : desc1,
       'desc2'      : desc2,
       'desc3'      : desc3,
       'desc4'      : desc4,
       'desc5'      : desc5,
       'accessoryName'  : accessoryName,
       'project'      : project,
       'locationStr'    : locationStr,
       'projectId'    : projectId,
       'projectName'    : projectName,
       'projectDesc'    : projectDesc},
       type: 'post',
       success: function(data) {
        console.log(data);
        $('#LoadingImage').hide();

      }
    });
    } 
    hideLoading();
  });



  function addPayDetails()
  {
    showLoading();
    var taxInvoiceNumber = $('[name="taxInvoiceNumber"]').children("option:selected").val();
    var receivedAmount = $('[name="receivedAmount"]')[0].value;
    var paymentMode = $('[name="paymentMode"]')[0].value;
    var projectId = $('[name="projectId"]')[0].value;

    if(receivedAmount==="" || paymentMode==="")
    {
      alert('Please add all the details and try Again..!!');
      hideLoading();
      return;
    }

    var paymentDetailsLine = '';

    $.ajax({
     url: "updatePaymentDetails",
     data: {'taxInvoiceNumber' : taxInvoiceNumber, 
     'receivedAmount' : receivedAmount, 
     'paymentMode' : paymentMode, 
     'projectId' : projectId},
     type: 'post',
     success: function(totalAmount) {

      if(totalAmount!=='Failure')
      {
        var fullDate = new Date();
        var twoDigitMonth = fullDate.getMonth()+"";if(twoDigitMonth.length==1)  twoDigitMonth="0" +twoDigitMonth;
        var twoDigitDate = fullDate.getDate()+"";if(twoDigitDate.length==1) twoDigitDate="0" +twoDigitDate;
        var currentDate = twoDigitDate + "/" + twoDigitMonth + "/" + fullDate.getFullYear();
		
		var idNum = parseFloat($($($($('#paymentDetailsBody')[0]).find('tr')[$($('#paymentDetailsBody')[0]).find('tr').length - 3]).find('th')[0]).text()) + 1;
		
        var amountPrnding = parseFloat(totalAmount) - parseFloat(receivedAmount); 
        $('#paymentDetailsBody').append('<tr><th>'+idNum+'</th><th>'+receivedAmount+'</th><th>'+paymentMode+'</th><th>'+currentDate+'</th></tr>');
      }
    alert('Payment details has been added successfully..!!');
    }
  });
    hideLoading();
  }



  function getValveDetails(nextTagName) {

   showLoading();
   var tag = '#'+nextTagName;
   var model = $('#model')[0].value;

   console.log("nextTagName is : "+ nextTagName);

   $.ajax({
     type : 'POST',
     data :  {'nextTagName' : nextTagName, 'model' : model},
     url : 'getValveDetails',
     success : function(data) {
      var blank = "<option> </option> <option value='-'>-</option>";
      $(tag+'Val').html(blank);
      $(tag+'Val').append(data);
      $('#LoadingImage').hide();
    }
  });
   hideLoading();
 }



  $(function() {
    $('button[name=generateBOQButton]').click(function(e) {
      e.preventDefault();

      if($('[name="sheetDetails"]').length===0)
      {
        alert('Please add atleast 1 sheet with Inventory Details and try again..!!');
        return;
      }

      var ele1 = $('[name="quantity"]');
      var length = $('#tableContentDetails').find(ele1).length;
      var stopNow = false;

      var sheetCount = $('[name="sheetDetails"]');
      
      try
      {
        for(var i=0;i<sheetCount.length;i++)
        {
          var eleLength = 0;


          eleLength = $('#'+sheetCount[i].value).find('tbody#tableContentDetails').find('input[name="inventoryName"]').length;

          if(eleLength===0)
          {
            alert('Please remove the blank sheet and try again..!!');
            return;
          }

          $('#'+sheetCount[i].value).find('tbody#tableContentDetails').find('input[name="sheetDetails"]').attr('value',sheetCount[i].value.split(',')[0]+','+eleLength);
        }
      }
      catch(e)
      {
        console.log(e);
      }

      for(var i=0; i < length; i++)
      {
        if($('#tableContentDetails').find(ele1)[i].value === "")
        {
          alert('Please enter Quantity for each element in BOQ');
          stopNow = true; 
          break;
        }
      }
      try{
        if(!stopNow)
        {
          $('[name="generateBOQ"]').validate();
          $('[name="generateBOQ"]').submit();
        }
      }
      catch(err)
      {
        console.log('Error occurred while generating BOQ : '+err);
      }

      var boqList = $('.revisionSection')[0].options; 

      var revisionNo = 0;
      for(var k = 0; k < boqList.length; k++)
      {
        if(boqList[k].value.startsWith($('[name="boqName"]').val()))
        {
          revisionNo ++;
          var optionVal = boqList[k].value;
          console.log(optionVal);        
        }

      }

      console.log('revisionNo is : '+revisionNo);

      var boqNameToAppend = '<option value="'+ $('[name="boqName"]').val() +'_R'+revisionNo+'">'+$('[name="boqName"]').val()+'_R'+revisionNo+'</option>';

      console.log("boqNameToAppend is : "+boqNameToAppend);

      $('.revisionSection').append(boqNameToAppend);

    });
  });



  function downloadInquiry(docType)
  {
    var fileToDownloadName = '';
    var isOffer = false;
    if(docType==='inquiry')
    {
      if($('.offerRevisionSection option:selected').val()==='')
      {
        alert('Please select the Inquiry to download');
        return;
      }
      fileToDownloadName = $('.offerRevisionSection option:selected').val();
      isOffer = true;
    }
    else if(docType==='boq')
    {
      if($('.revisionSection').val()==='')
      {
        alert('Please select the BOQ to download');
        return;
      }
      fileToDownloadName = $('.revisionSection').val();
    }

    var newForm = jQuery('<form>', {
      'action': 'download',
      'method': 'POST'
    }).append(jQuery('<input>', {
      'name': 'isOffer',
      'value': isOffer,
      'type': 'hidden'
    })).append(jQuery('<input>', {
      'name': 'projectId',
      'value': $('[name="projectId"]')[0].value,
      'type': 'hidden'
    })).append(jQuery('<input>', {
      'name': 'docNameToDownload',
      'value': fileToDownloadName,
      'type': 'hidden'
    }));
    $(document.body).append(newForm);
    newForm.submit();
  }




  function deleteRevision(docType)
  {
    var fileToDownloadName = '';
    if(docType==='inquiry')
    {
      if($('.offerRevisionSection option:selected').val()==='')
      {
        alert('Please select the Inquiry to delete');
        return;
      }

      fileToDownloadName = $('.offerRevisionSection option:selected').val();
    }
    else if(docType==='boq')
    {
      if($('#boqName')[0].value==='')
      {
        alert('Please select the BOQ to delete');
        return;
      }

      fileToDownloadName = $('#boqName')[0].value;
    }

    var newForm = jQuery('<form>', {
      'action': 'delete',
      'method': 'POST'
    }).append(jQuery('<input>', {
      'name': 'projectId',
      'value': $('[name="projectId"]')[0].value,
      'type': 'hidden'
    })).append(jQuery('<input>', {
      'name': 'docNameToDownload',
      'value': fileToDownloadName,
      'type': 'hidden'
    }));
    $(document.body).append(newForm);
    newForm.submit();
  }






  function generateInvoice() 
  {
    var CheckeleCount = $('#generateInvoiceTable input.checkbox').length;

    var selectedElements = [];
    var i;

    for(i=0; i < CheckeleCount; i++)
    {
      if($('#generateInvoiceTable input.checkbox')[i].checked)
      {
       selectedElements[i] = i;
     }
   }

   if(selectedElements.length === 0)
   {
    alert('Please select an element to generate the Invoice');
    return;
  }

  showLoading();
  
  var eleCount = $('#generateInvoiceTable input').length;

  var inventoryName        = [];
  var material             = [];
  var type                 = [];
  var manifacturingMethod  = [];
  var classOrGrade         = [];
  var ends                 = [];
  var size                 = [];
  var purchaseRate       = [];
  var receivedQuantity     = [];
  var location         = [];
  var receivedDate         = [];

  var contactName          = $('[name="contactName"]')[0].value;
  var mobileNo           = $('[name="mobileNo"]')[0].value;
  var addressedto1         = $('[name="addressedto1"]')[0].value;
  var orderDate          = $('[name="orderDate"]')[0].value;
  var emailAddress       = $('[name="emailAddress"]')[0].value;
  var invoiceType        = $('[name="invoiceType"]')[0].value;
  var hsnOrSac         = $('[name="hsnOrSac"]')[0].value;

  var j;
  var k = 0;
  var n = 1;
  for(k=0;k<selectedElements.length;k++)
  {

   if(selectedElements[k] != undefined)
   {
    var start = 1 + 14*parseFloat(selectedElements[k]);

    inventoryName[k]      = $('#generateInvoiceTable input')[start++].value;
    material[k]       = $('#generateInvoiceTable input')[start++].value;
    type[k]         = $('#generateInvoiceTable input')[start++].value;
    manifacturingMethod[k]  = $('#generateInvoiceTable input')[start++].value;
    classOrGrade[k]     = $('#generateInvoiceTable input')[start++].value;
    ends[k]         = $('#generateInvoiceTable input')[start++].value;
    size[k]         = $('#generateInvoiceTable input')[start++].value;
    purchaseRate[k]     = $('#generateInvoiceTable input')[start++].value;                    
    receivedQuantity[k]   = $('#generateInvoiceTable input')[start++].value;
    start++;     
    location[k]       = $('#generateInvoiceTable input')[start++].value;
    receivedDate[k]     = $('#generateInvoiceTable input')[start++].value;

  } 
}

    var lastArray = 3 + 20*parseFloat(selectedElements[parseFloat(selectedElements.length) - 1]);


    var formData = $(this).serializeArray();

    formData.push({name: 'projectId', value: $('[name="projectId"]').val()});

    var inventoryName_string = cleanArray(inventoryName);
    formData.push({name: 'inventoryName', value: inventoryName_string});
    var material_string = cleanArray(material);
    formData.push({name: 'material', value: material_string});
    var type_string = cleanArray(type);
    formData.push({name: 'type', value: type_string});
    var manifacturingMethod_string = cleanArray(manifacturingMethod);
    formData.push({name: 'manifMetod', value: manifacturingMethod_string});
    var classOrGrade_string = cleanArray(classOrGrade);
    formData.push({name: 'classOrGrade', value: classOrGrade_string});
    var ends_string = cleanArray(ends);
    formData.push({name: 'ends', value: ends_string});
    var size_string = cleanArray(size);
    formData.push({name: 'size', value: size_string});
    var purchaseRate_string = cleanArray(purchaseRate);
    formData.push({name: 'purchaseRate', value: purchaseRate_string});
    var receivedQuantity_string = cleanArray(receivedQuantity);
    formData.push({name: 'receivedQuantity', value: receivedQuantity_string});
    var location_string = cleanArray(location);
    formData.push({name: 'location', value: location_string});
    var receivedDate_string = cleanArray(receivedDate);
    formData.push({name: 'receivedDate', value: receivedDate_string});

    formData.push({name: 'contactName', value: contactName});
    formData.push({name: 'mobileNo', value: mobileNo});
    formData.push({name: 'addressedto1', value: addressedto1});
    formData.push({name: 'orderDate', value: orderDate});
    formData.push({name: 'emailAddress', value: emailAddress});
    formData.push({name: 'invoiceType', value: invoiceType});
    formData.push({name: 'hsnOrSac', value: hsnOrSac});

    $.ajax({
     url: "generateInvoice",
     data: formData,
     type: 'post',
     success: function(data)
     {

      var CheckeleCount1 = $('#generateInvoiceTable input.checkbox').length;

      var selectedElements1 = [];
      var j;

      for(j=0; j < CheckeleCount1; j++)
      {
        if($('#generateInvoiceTable input.checkbox')[j].checked)
        {
         selectedElements1[j] = j;
       }


     }

     var deleted = 0;
     for(var d=0;d<selectedElements1.length;d++)
     {
       if(selectedElements1[d] != undefined)
       {
        $('#generateInvoiceTable tr')[d-deleted].remove();
        deleted++;
      }
    }
     alert('Invoice generated Successfully. Please check the Notification section to verify and send.');
    },
    error : function (error) {
        alert('Error generating Invoice..!!');
    }
    });
hideLoading();
}

function cleanArray(actual)
{
  var newArray = new Array();
  for(var i = 0; i<actual.length; i++)
  {
    if (actual[i])
    {
      newArray.push(actual[i]);
    }
  }
  return newArray;
}



  $('#generateInvoiceFrNInv').on('click', function(){

    showLoading();

    $.ajax({
     type : 'POST',
     data :  {'projectId' : $('[name="projectId"]')[0].value},
     url : 'getNoInvoiceInventory',
     success : function(data) 
     {
      $('#generateInvoiceTable').html('');
      $('#generateInvoiceTable').append(data);
    }
  });

    hideLoading();

  });



  function addNewSheet()
  {
    if($('[name="newSheetName"]').val()==="")
    {
      alert('Please enter a Sheet name..!!');
      return;
    }
    var sheetName = $('[name="newSheetName"]').val();
    var tab = '<li class="nav-item"><a class="nav-link '+sheetName+'" href="#'+sheetName+'" role="tab" data-toggle="tab" aria-selected="true">'+sheetName+'<i class="fa fa-times pr-2" onClick="removeSheet('+sheetName+');" style="margin-left:5px;"></i></a></li>';

    $('#sheetListtableContentDetails').append(tab);

    var tabPane = '<div class="tab-pane fade" id="'+sheetName+'" role="tabpanel"><div class="row" style="margin-top:2%;">     <div class="col-md-12 ">       <div class="table-responsive">                        <table class="table table-colored inventoryDetails inventoryTableHeader" style="display: none;">         <thead>           <tr>            <th>#</th>            <th>Invnt</th>            <th>Material</th>            <th>Type</th>            <th>Method</th>            <th>Grd/Cls</th>            <th>Ends</th>            <th>Size</th>            <th>Qty</th>            <th>Base Supply Rate</th>            <th>Supply Rate</th>            <th>Base Erection Rate</th>            <th>Erection Rate</th>            <th>Supply Amount</th>            <th>Erection Amount</th>          </tr>          <tr>            <th></th>            <th></th>            <th></th>            <th></th>            <th></th>            <th></th>            <th></th>            <th></th>            <th></th>            <th><input type="text" style="width:45px;" name="supplyPrsnt" onChange="updateSupplyRate($(this));"/></th>            <th></th>            <th><input type="text" style="width:45px;" name="erectionPrsnt" onChange="updateErectionRate($(this));"/></th>            <th></th>            <th></th>            <th></th>          </tr>        </thead>        <tbody id="tableContentDetails">        </tbody>      </table>    </div>  </div></div><div class="separator clearfix"></div><p class="text-right"><strong>SubTotal :</strong> <span id="'+sheetName+'SupSubTotal">0.00</span> <span id="'+sheetName+'EreSubTotal">0.00</span></p></div>';

    $($('.tab-content')[1]).append(tabPane); 

    $('#'+sheetName).find('tbody#tableContentDetails').append('<input type="hidden" name="sheetDetails" id="sd'+sheetName+'" value="'+sheetName+'">');


    $('.nav-link '+sheetName).trigger('click');

  }



  function removeSheet(sheetName)
  {
   console.log(sheetName);
   var name = sheetName.attributes.id.value;
   $('.'+name).remove();
   $('#tableContentDetails').find('div#'+name).remove();

 }



  $(document).ready(function(){

    var vList = $('[name="venderList"]').val().split(',');
    for(var i = 0; i < vList.length ; i++)
    {
     var venderName = vList[i];

     if(venderName != "")
      $("[name='selectedVenderName']").append("<option value=" + venderName + ">" + venderName + "</option>");
  }

});


  
  $(function() {

    //BOQ Secsion  
    $('#createProjectModal1').on('hidden.bs.tab', function () {
      $($($('[name="generateBOQ"]')[0]).find('[name="sheetDetails"]')[0]).remove();
      $($('#sheetListtableContentDetails')[0]).html('');
      $('#tableContentDetails').html('');
    });

    //Inquiry Section
    $('#createProjectModal2').on('hidden.bs.tab', function () {
      $($($('[name="generateBOQ"]')[0]).find('[name="sheetDetails"]')[0]).remove();
      $($('#sheetListtableContentInqSec')[0]).html('');
      $('#tableContentInqSec').html('');
    });

    //PO section
    $('#createProjectModal3').on('hidden.bs.tab', function () {
      $($($('[name="generateBOQ"]')[0]).find('[name="sheetDetails"]')[0]).remove();
      $($('#sheetListtableContentPOSec')[0]).html('');
      $('#tableContentPOSec').html('');
    });
  });



  function closeOther(modalToShow)
  {
    if(modalToShow!=="payDetails")
      $('#payDetails').attr('class','collapse');
    
    if(modalToShow!=="createProjectModal5")
      $('#createProjectModal5').attr('class','collapse');
    
    if(modalToShow!=="generateInvoice")
      $('#generateInvoice').attr('class','collapse');
  }




  function removeItem(thisObj)
  {
    console.log(thisObj.parent().parent().remove());
  }




  $('[name="selectedVenderName"]').on('change', function(e){

    var projName = $('#projectName')[0].innerHTML;
    var venderName = $('[name="selectedVenderName"]').children("option:selected").val();
    $('[name="inquiryName"]').attr('value', venderName.replace(/ /g, "_")+"_"+projName.replace(/ /g, "_"));
  });



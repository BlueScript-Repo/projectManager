<!DOCTYPE html>
<html dir="ltr" lang="zxx">

<head>
    <meta charset="utf-8">
    <title>Hamdule Project</title>
    <meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
    <meta name="author" content="author">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico">

    <!-- Web Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=PT+Serif" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="css/custTooltip.css" rel="stylesheet">

    <!-- Font Awesome CSS -->
    <link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Plugins -->
    <link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
    <link href="css/animations.css" rel="stylesheet">
    <link href="plugins/slick/slick.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">

    <link href="css/typography-default.css" rel="stylesheet">
    <link href="css/skins/light_blue.css" rel="stylesheet">

    <!-- Custom css -->
    <link href="css/custom.css" rel="stylesheet">
</head>
<body class="" cz-shortcut-listen="true">

    <div class="scrollToTop circle fadeToTop"><i class="fa fa-angle-up"></i></div>

    <div class="page-wrapper">    
        <#include "./header.ftl">
        <section class="main-container">
            <section class="light-gray-bg pv-30 padding-bottom-clear clearfix">
                <div class="container" style="max-width:98%;">
                    <div class="row">
                        <div class="col-md-7">
                            <div class="pv-30 ph-20 feature-box bordered shadow text-center object-non-visible animated object-visible fadeInDownSmall"
                            data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                            <h3 id="projectName">${projectName}</h3>
                            <div class="separator clearfix"></div>
                            <p>${projectDesc}</p>
                        </div>
                    </div>
                    <aside class="col-md-5 col-xl-5 ml-xl-auto">
                        <div class="sidebar">
                            <div class="block clearfix">
                                <div class="clearfix">
                                    <h3 class="title float-left">Project Details</h3>
                                    <form action="deleteProject">
                                        <input type="hidden" name="projectId" value="${projectId}">
                                        <input type="submit" class="btn btn-default title  ml-5"
                                        value="Delete This Project">
                                    </form>
                                </div>
                                <div class="separator-2"></div>
                                <ul class="list margin-clear">
                                    <li>
                                        <strong>PO Date: </strong> <span class="text-right">${poDate}</span> <strong
                                        style="padding-left: 19%;">PO Number:</strong> <span>${poNumber}</span>
                                    </li>
                                    <li>
                                        <strong>Contact Person: </strong> <span class="text-right">${contactName}</span>
                                        <strong style="padding-left: 15%;">Contact Number: </strong> <span
                                        class="text-right">${contactPhone}</span>
                                    </li>
                                    <li>
                                        <strong>Address: </strong> <span class="text-right">${address}</span>
                                        <strong style="padding-left: 10%;">Contact Email: </strong>
                                        <span class="text-right">${contactEmail}</span>
                                    </li>
                                    <li>
                                        <strong>GST #: </strong> <span class="text-right">${gstNumber}</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </aside>
                </div>
                <br>
            </div>
            <div class="container" style="max-width:98%;">
                <div class="border border-dark rounded-top">
                    <ul class="nav nav-tabs style-1" role="tablist">
                        <li class="nav-item" style="${showProjectDetails}">
                            <a class="nav-link active show" href="#vtab1" role="tab" data-toggle="tab"
                            aria-selected="true"><i class="fa fa-magic pr-2"></i> Project Details</a>
                        </li>
                        <li class="nav-item" style="${showBOQSection}">
                            <a class="nav-link" id="createProjectModal1" href="#vtab2" role="tab" data-toggle="tab"
                            aria-selected="false"><i class="fa fa-life-saver pr-2"></i>BOQ Section</a>
                        </li>
                        <li class="nav-item" style="${showInquirySection}">
                            <a class="nav-link" id="createProjectModal2" href="#vtab3" role="tab" data-toggle="tab"
                            aria-selected="false"><i class="fa fa-life-saver pr-2"></i>Inquries Section</a>
                        </li>
                        <li class="nav-item" style="${showPOSection}">
                            <a class="nav-link" id="createProjectModal3" href="#vtab4" role="tab" data-toggle="tab"
                            aria-selected="false"><i class="fa fa-life-saver pr-2"></i>PO Section</a>
                        </li>
                        <li class="nav-item" style="${showInvoiceSection}">
                            <a class="nav-link" href="#vtab5" role="tab" data-toggle="tab" aria-selected="false"><i
                                class="fa fa-life-saver pr-2"></i>Invoice Section</a>
                            </li>
                            <li class="nav-item" style="${showInventorySection}">
                                <a class="nav-link" href="#vtab6" role="tab" data-toggle="tab" aria-selected="false"><i
                                    class="fa fa-life-saver pr-2"></i>Inventory Section</a>
                                </li>
                            </ul>
                            <div class="tab-content" style="width:98%;">
                                <div class="tab-pane fade active show" id="vtab1" role="tabpanel">
                                    <form action="updateProject" class="projDetails" method="POST">
                                        <div class="row" style="margin-top: 2%;">
                                            <div class="col-md-3 ">
                                                <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                                                data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                                                <div class="form-group has-feedback">
                                                    <label style="margin-left: -80%;">PO Date</label>
                                                    <input type="text" name="poDate" class="form-control" value="" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                                            data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                                            <label style="margin-left: -75%;">PO Number</label>
                                            <input type="text" name="poNumber" class="form-control" value="" required>
                                        </div>
                                    </div>
                                    <div class="col-md-3 ">
                                        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                                        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                                        <label style="margin-left: -55%;">Contact Person Name</label>
                                        <input type="text" name="contactName" class="form-control" required>
                                    </div>
                                </div>
                                <div class="col-md-3 ">
                                    <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                                    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                                    <label style="margin-left: -70%;">Contact Email</label>
                                    <input type="text" name="contactEmail" class="form-control" required>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" style="margin-top: -3%;margin-bottom: 2%">
                            <div class="col-md-3 ">
                                <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                                data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                                <label style="margin-left: -70%;">Contact Phone</label>
                                <input type="text" name="contactPhone" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-3 ">
                            <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                            data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                            <label style="margin-left: -70%;">GST Number</label>
                            <input type="text" name="gstNumber" class="form-control" value="" required>
                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                        <label style="margin-left: -80%;">Address</label>
                        <input type="text" name="address" class="form-control" required>
                    </div>
                </div>


                <div class="col-md-3 " style="margin-left: -2%;">
                    <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
                    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
                    <label></label>
                    <br>
                    <button type="submit" class="btn btn-default">Update Project</button>
                </div>
            </div>
        </div>
        <input type="hidden" name="projectId" value="${projectId}">
    </form>
</div>
<div class="tab-pane fade" id="vtab2" role="tabpanel">
    <div class="row">
        <div class="col-md-3" align="center">
            <h4>Quotation Revisions</h4>
            <select class="form-control revisionSection"
            onchange="download($('.revisionSection').val(),'tableContentDetails');">
            <option></option>
        </select>
    </div>
    <div class="col-md-3" align="center">
        <br/>
        <button type="button" class="btn btn-default" onclick="toggleCreateBOQSec();">Create BOQ
        </button>
    </div>
    
    <div class="col-md-3" align="center">
        <br/>
        <form name="fileUploadForm" action="import" method="post"
        enctype="multipart/form-data" style="margin: 0px">
        <button class="btn btn-default" name="uploadFile">Upload</button>
        <input type="file" name="file" onClick="toggleImportSec();" class="upload-box">
    </form>
</div>
</div>
<div class="row" style="margin-left: 2%" ;>
    <input class="form-control" type="hidden" name="boqNameList" value="${boqNameList}"
    style="margin-left: 3%;margin-bottom: 6%;">
    <input class="form-control" type="hidden" name="quotationNamesList"
    value="${quotationNamesList}" style="margin-left: 3%;margin-bottom: 6%;">
    <input class="form-control" type="hidden" name="taxInvoiceNamesList"
    value="${taxInvoiceNamesList}" style="margin-left: 3%;margin-bottom: 6%;">
    <input class="form-control" type="hidden" name="poNamesList" value="${poNamesList}"
    style="margin-left: 3%;margin-bottom: 6%;">
</div>
<div class="row" style="margin-bottom: 2%;">
    <div class="col-md-12">
        <div class="separator object-non-visible mt-10 animated object-visible fadeIn"
        data-animation-effect="fadeIn" data-effect-delay="100"></div>
    </div>
</div>
<form name="generateBOQ" action="generateNew" class="createBOQ" method="POST" id="generateBOQ"
style="display: none;">
<div class="row" style="margin-left: 8%;">
    <div class="col-md-3 col-sm-3">
        <div class="animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
        <label>Client</label>
        <input type="text" name="client" class="form-control" value="${clientName}"
        required>
    </div>
</div>
<div class="col-md-3 col-sm-3">
    <div class="animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    <label>Site</label>
    <input type="text" name="site" class="form-control" value="" required>
</div>
</div>
<div class="col-md-3 col-sm-3">
    <label class="createBOQ" style="margin-top: 1%; display: none;">BOQ Name
    : </label>
    <input class="createBOQ form-control" style="display: none;" type="text"
    id="boqName" name="boqName" value="" project="" required>
    <input type="hidden" name="projectId" value="${projectId}"/>
</div>
<div class="col-md-3 col-sm-3">
    <div class="animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    <label>Utility</label>
    <input type="text" name="utility" class="form-control" value="${utility}"
    >
</div>
</div>
</div>
<div class="row" style="margin-left: 8%;margin-top: 1%;">
    <div class="col-md-3 col-sm-3">
        <div class="animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
        <label>Doc Name</label>
        <input type="text" name="dName" class="form-control" value="" >
    </div>
</div>
<div class="col-md-3 col-sm-3">
    <div class="animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    <label>Doc Number</label>
    <input type="text" class="form-control" name="dNo" value="" >
</div>
</div>
<div class="col-md-3 col-sm-3">
    <div class="animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    <label>Pressure</label>
    <input type="text" name="pressure" class="form-control" value="" >
</div>
</div>
<div class="col-md-3 col-sm-3" style="margin-left: 0%;">
    <div class="animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    <label>Temperature</label>
    <input type="text" name="temperature" class="form-control" value=""
    >
</div>
</div>
</div>
<div class="row" style="margin-left: 8%;margin-top: 1%;">
    <div class="col-md-6">
        <label class="createBOQ" style="margin-top: 1%; display: none;">Terms : </label>
        <textarea name="paymentTerms" class="form-control">FOR SUPPLY: &#10; &#10;FOR LABOR:  </textarea>
        <small>Add max 4 terms each</small>
    </div>
</div>
<div class="row">
    <div class="col-md-12 pl-5">
        <ul class="nav nav-tabs style-1" role="tablist"
        id="sheetListtableContentDetails" style="margin-top:3%;">
    </ul>
    <div class="tab-content" id="tableContentDetails">
    </div>
</div>
</div>
<div class="row" style="margin-top: 2%;">
    <div class="col-md-1" align="center">
     <input type="button" class="btn btn-default" name="deleteItemButton"
        onclick="deleteItem('boq');" value="Delete Item">
    </div>
    <div class="col-md-2" align="center">
        <input type="button" class="btn btn-default" name="deleteBoqButton"
        onclick="deleteRevision('boq');" value="Delete This BOQ">
    </div>
    <div class="col-md-2" align="center">
        <input type="button" class="btn btn-default" name="downloadBoqButton"
        onclick="downloadInquiry('boq');" value="Download This BOQ">
    </div>
    <div class="col-md-2" align="center">
        <button type="submit" name="generateBOQButton"
        class="btn btn-default generateBOQButton">Generate Quotation
    </button>
</div>
<div class="col-md-2" align="center">
    <small>Enter sheet name and click "Add New Sheet"</small>
    <input type="text" class="form-control" style="margin-top: 5px;width: 150px;"
    name="newSheetName">
    <br>
    <h4 class="btn btn-default" data-toggle="collapse" onClick="addNewSheet()">Add
    New Sheet</h4>
</div>
<div class="col-md-2" align="center">
    <small>Select items from below to be added to the sheet</small>
    <select class="form-control" onchange="hideOthers(this.value)"
    style="width: 100%;">
    <option></option>
    <option value="item1">Add Pipes & Fittings</option>
    <option value="valve1">Add Valves</option>
    <option value="accessory1">Add Accessories & Equipments</option>
    <option value="accessory1">Add Fabrication works</option>
</select>
</div>
</div>
<div class="row" style="margin-top: 2%;">
    <div class="col-md-12">
        <div class="separator object-non-visible mt-10 animated object-visible fadeIn"
        data-animation-effect="fadeIn" data-effect-delay="100"></div>
    </div>
</div>
</form>
<div class="container collapse" id="item1" style="max-width:98%;" id="addInventory">
    <div class="row createBOQ" style="display: none;">
        <div class="col-md-12 form-inline">
            <table class="table table-colored border border-info" style="max-width: 98%;">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>MOC</th>
                        <th>Manufacture Type</th>
                        <th>Class/Grade</th>
                        <th>Material Specs</th>
                        <th>Standard Type</th>
                        <th>Ends</th>
                        <th>Size</th>
                    </tr>
                </thead>
                <tbody id="tableContent">
                    <tr>
                        <td>
                            <div class="form-group">
                                <select class="form-control" name="product"
                                id="product"
                                onchange="myFunction('moc');">
                                <option></option>
                                ${items}
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="moc" id="moc"
                            onchange="myFunction('manufactureType');">
                            <option></option>
                        </select>
                    </div>
            </td>
            <td>
                <div class="form-group">
                    <select class="form-control" name="manufactureType"
                    id="manufactureType" onchange="myFunction('classOrSch');">
                    <option></option>                    
                </select>
            </div>
        </td>
        <td>
            <div class="form-group">
                <select class="form-control" name="classOrSch"
                id="classOrSch" onchange="myFunction('materialSpecs');">
                <option></option>
            </select>
        </div>
    </td>
    <td>
        <div class="form-group">
            <select class="form-control" name="materialSpecs"
                id="materialSpecs" onchange="myFunction('standardType');">
                <option></option>
            </select>
        </div>
    </td>
    <td>
        <div class="form-group">
            <select class="form-control" name="standardType"
                id="standardType">
                <option></option>
            </select>
        </div>
    </td>
    <td>
        <div class="form-group">
            <select class="form-control" name="ends" id="ends">
                <option></option>
                <option>Buttweld</option>
                <option>Socket-Weld</option>
                <option>Threaded</option>
                <option>Plain-End</option>
            </select>
        </div>
    </td>
    <td>
        <div class="form-group">
            <select class="form-control" name="size" id="size">
                <option value='15NB-1/2"'>15NB-1/2"</option>
                <option value='20NB-3/4"'>20NB-3/4"</option>
                <option value='25NB-1"'>25NB-1"</option>
                <option value='32NB-1:1/4"'>32NB-1:1/4"</option>
                <option value='40NB-1:1/2"'>40NB-1:1/2"</option>
                <option value='50NB-2"'>50NB-2"</option>
                <option value='65NB-2:1/2"'>65NB-2:1/2"</option>
                <option value='80NB-3"'>80NB-3"</option>
                <option value='100NB-4"'>100NB-4"</option>
                <option value='125NB-5"'>125NB-5"</option>
                <option value='150NB-6"'>150NB-6"</option>
                <option value='200NB-8"'>200NB-8"</option>
                <option value='250NB-10"'>250NB-10"</option>
                <option value='300NB-12"'>300NB-12"</option>
                <option value='350NB-14"'>350NB-14"</option>
                <option value='400NB-16"'>400NB-16"</option>
                <option value='450NB-18"'>450NB-18"</option>
                <option value='500NB-20"'>500NB-20"</option>
                <option value='550NB-22"'>550NB-22"</option>
                <option value='600NB-24"'>600NB-24"</option>
            </select>
        </div>
    </td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="row createBOQ" style="display: none;">
    <div class="col-md-2">
        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
        <label></label>
        <br>

        <button class="btn btn-default" onclick="appendInventory();">Add Inventory</button>

    </div>
</div>
</div>
</div>
<div class="container collapse" id="accessory1" style="max-width:98%;" id="addAccessory">
    <div class="row createBOQ" style="display: none;">
        <div class="col-md-12">
            <table class="table table-colored createBOQ rounded-circle border border-info"
            style="display:none;">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Desc1</th>
                    <th>Desc2</th>
                    <th>Desc3</th>
                    <th>Desc4</th>
                    <th>Desc5</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input class="form-control" type="text" name="accessoryName"
                     id="accessoryName"/></td>
                     <td><input class="form-control" type="text" name="desc1" id="desc1"/>
                     </td>
                     <td><input class="form-control" type="text" name="desc2" id="desc2"/>
                     </td>
                     <td><input class="form-control" type="text" name="desc3" id="desc3"/>
                     </td>
                     <td><input class="form-control" type="text" name="desc4" id="desc4"/>
                     </td>
                     <td><input class="form-control" type="text" name="desc5" id="desc5"/>
                     </td>
                 </tr>
             </tbody>
         </table>
     </div>
 </div>
 <div class="row createBOQ" style="display: none;">
    <div class="col-md-2">
        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
        <label></label>
        <br>
        <button type="button" class="btn btn-default" onClick="appendAccessory();">
            Add Accessory
        </button>

    </div>
</div>
</div>
</div>
<div class="container collapse" id="valve1" style="max-width:90%;" id="addValve">
    <div class="row createBOQ" style="display: none;">
        <div class="col-md-12 form-inline">
            <table class="table table-colored createBOQ rounded-circle border border-info"
            style="display:none;">
            <thead>
                <tr>
                    <th>Model</th>
                    <th>Material</th>
                    <th>End</th>
                    <th>Type</th>
                    <th>Pressure Ratings</th>
                    <th>Max Inlet Pressure</th>
                    <th>Operations</th>
                    <th>SeatAndSeals</th>
                    <th>Valve Size</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="model" id="model"
                            onchange="getValveDetails('material');">
                            <option></option>
                            <option value="CAST_IRON_GATE:API-600">CAST IRON
                                GATE-API-600
                            </option>
                            <option value="CAST_IRON_GATE:API6D">CAST IRON GATE-API6D
                            </option>
                            <option value="CAST_IRON_GATE:MSS-SP-81">CAST IRON GATE-MSS
                                SP-81
                            </option>
                            <option value="CAST_IRON_GLOBE:API-600">CAST IRON
                                GLOBE-API-600
                            </option>
                            <option value="CAST_IRON_GLOBE:API6D">CAST IRON
                                GLOBE-API6D
                            </option>
                            <option value="CAST_IRON_GLOBE:MSS-SP-81">CAST IRON
                                GLOBE-MSS SP-81
                            </option>
                            <option value="SLUICE_&_REFLUX:API-600">SLUICE &
                                REFLUX-API-600
                            </option>
                            <option value="SLUICE_&_REFLUX:API6D">SLUICE &
                                REFLUX-API6D
                            </option>
                            <option value="SLUICE_&_REFLUX:MSS-SP-81">SLUICE &
                                REFLUX-MSS SP-81
                            </option>
                            <option value="STAINERS:ARC 014 ">STAINERS-ARC 014</option>
                            <option value="GATE:API 602">GATE-API 602</option>
                            <option value="GATE:BS-5352">GATE-BS-5352</option>
                            <option value="GATE:ARC-024">GATE-ARC-024</option>
                            <option value="GLOBE:API 602">GLOBE-API 602</option>
                            <option value="GLOBE:BS-5352">GLOBE-BS-5352</option>
                            <option value="GLOBE:ARC-024">GLOBE-ARC-024</option>
                            <option value="CHECK:API 602">CHECK-API 602</option>
                            <option value="CHECK:BS-5352">CHECK-BS-5352</option>
                            <option value="CHECK:ARC-024">CHECK-ARC-024</option>
                            <option value="DIAPHRAGM:BS-5156">DIAPHRAGM-BS-5156</option>
                            <option value="SAFETY:API 526">SAFETY-API 526</option>
                            <option value="STEAM_TRAPS:IS 12268">STEAM TRAPS-IS 12268
                            </option>
                            <option value="GATE:API 600"> GATE-API 600</option>
                            <option value="GATE:API-6D"> GATE-API 6D</option>
                            <option value="GATE:MSS SP-81"> GATE-MSS SP-81</option>
                            <option value="KNIFE_EDGE_GATE:API 600">KNIFE EDGE GATE-API
                                600
                            </option>
                            <option value="KNIFE_EDGE_GATE:API-6D">KNIFE EDGE GATE-API
                                6D
                            </option>
                            <option value="KNIFE_EDGE_GATE:MSS SP-81">KNIFE EDGE
                                GATE-MSS SP-81
                            </option>
                            <option value="GLOBE:BS-1873">GLOBE-BS-1873</option>
                            <option value="GLOBE:ND-16">GLOBE-ND-16</option>
                            <option value="GLOBE:ND-40">GLOBE-ND-40</option>
                            <option value="NON_RETURN:BS-1868">NON RETURN-BS-1868
                            </option>
                            <option value="NON_RETURN:API~6D">NON RETURN-API 6D</option>
                            <option value="FLOATING&TRUNION:BS-5351">
                                FLOATING&TRUNION-BS-5351
                            </option>
                            <option value="FLOATING&TRUNION:API_6D">FLOATING&TRUNION-API
                                6D
                            </option>
                            <option value="FLOATING&TRUNION:BS5159">
                                FLOATING&TRUNION-BS5159
                            </option>
                            <option value="FLOATING&TRUNION:ISO 17292">
                                FLOATING&TRUNION-ISO 17292
                            </option>
                            <option value="BUTTERFLY:BS-5155">BUTTERFLY-BS-5155</option>
                            <option value="BUTTERFLY:API 609">BUTTERFLY-API 609</option>
                            <option value="BUTTERFLY:AWWA C504">BUTTERFLY-AWWA C504
                            </option>
                            <option value="BUTTERFLY:IS 13095">BUTTERFLY-IS 13095
                            </option>
                            <option value="WAFER:API.6D">WAFER-API 6D</option>
                            <option value="WAFER:API 594">WAFER-API 594</option>
                            <option value="DUALPLATE_CHECK:API.6D">DUALPLATE CHECK-API
                                6D
                            </option>
                            <option value="DUALPLATE_CHECK:API 594">DUALPLATE CHECK-API
                                594
                            </option>
                        </select>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <select class="form-control" name="material" id="materialVal"
                        onchange="getValveDetails('end');">
                        <option></option>
                    </select>
                </div>
            </td>
            <td>
                <div class="form-group">
                    <select class="form-control" name="end" id="endVal"
                    onchange="getValveDetails('type');">
                    <option></option>
                </select>
            </div>
        </td>
        <td>
            <div class="form-group">
                <select class="form-control" name="type" id="typeVal"
                onchange="getValveDetails('pressureRatings');">
                <option></option>
            </select>
        </div>
    </td>
    <td>
        <div class="form-group">
            <select class="form-control" name="pressureRatings"
            id="pressureRatingsVal"
            onchange="getValveDetails('maxInletPressure');">
            <option value='-'>-</option>
        </select>
    </div>
</td>
<td>
    <div class="form-group">
        <select class="form-control" name="maxInletPressure"
        id="maxInletPressureVal"
        onchange="getValveDetails('operations');">
        <option value='-'>-</option>
    </select>
</div>
</td>
<td>
    <div class="form-group">
        <select class="form-control" name="operations"
        id="operationsVal"
        onchange="getValveDetails('seatAndSeals');">
        <option value='-'>-</option>
    </select>
</div>
</td>
<td>
    <div class="form-group">
        <select class="form-control" name="seatAndSeals"
        id="seatAndSealsVal">
        <option value='-'>-</option>
    </select>
</div>
</td>
<td>
    <div class="form-group">
        <select class="form-control" name="valveSize" id="valveSize">
            <option value='15NB-1/2"'>15NB-1/2"</option>
            <option value='20NB-3/4"'>20NB-3/4"</option>
            <option value='25NB-1"'>25NB-1"</option>
            <option value='32NB-1:1/4"'>32NB-1:1/4"</option>
            <option value='40NB-1:1/2"'>40NB-1:1/2"</option>
            <option value='50NB-2"'>50NB-2"</option>
            <option value='65NB-2:1/2"'>65NB-2:1/2"</option>
            <option value='80NB-3"'>80NB-3"</option>
            <option value='100NB-4"'>100NB-4"</option>
            <option value='125NB-5"'>125NB-5"</option>
            <option value='150NB-6"'>150NB-6"</option>
            <option value='200NB-8"'>200NB-8"</option>
            <option value='250NB-10"'>250NB-10"</option>
            <option value='300NB-12"'>300NB-12"</option>
            <option value='350NB-14"'>350NB-14"</option>
            <option value='400NB-16"'>400NB-16"</option>
            <option value='450NB-18"'>450NB-18"</option>
            <option value='500NB-20"'>500NB-20"</option>
            <option value='550NB-22"'>550NB-22"</option>
            <option value='600NB-24"'>600NB-24"</option>
        </select>
    </div>
</td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="row createBOQ" style="display: none;">
    <div class="col-md-2">
        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
        <label></label>
        <br>
        <button type="button" class="btn btn-default" onclick="appendValve();">Add Valve
        </button>
    </div>
</div>
</div>
</div>
<div class="form-row createBOQ" style="display: none;">
    <div class="col-md-4 ">
        <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
        data-animation-effect="fadeInDownSmall" data-effect-delay="100">
    </div>
</div>
<div class="col-md-4 ">
    <div class="ph-20 feature-box text-center object-non-visible animated object-visible fadeInDownSmall"
    data-animation-effect="fadeInDownSmall" data-effect-delay="100">
</div>
</div>
<div class="col-md-4 ">
</div>
</div>
<br>
<div class="col-md-3 ">
</div>
</div>
<div class="tab-pane fade pl-5" id="vtab3" role="tabpanel">
    <ul class="nav nav-tabs style-1" role="tablist" id="sheetListtableContentInqSec">
    </ul>
    <div class="tab-content" id="tableContentInqSec">
    </div>
    <div class="row" style="margin-top: 2%;margin-bottom: 2%;">
        <div class="col-md-3" style="margin-left: 5%;">
            <h4>Quotation Revisions</h4>
            <select class="form-control revisionSection"
            onchange="download($(this).val(), 'tableContentInqSec');">
            <option></option>
        </select>
        <br>
        <h4>Inquiry List</h4>
        <select class="form-control offerRevisionSection"
        onchange="download($(this).val(),'tableContentInqSec');">
        <option></option>
    </select>
</div>
<div class="col-md-3" style="margin-left: -5%;">
    <input type="hidden" name="venderList" value="${venderList}">
    <h4>Vender List</h4>
    <select class="form-control venderList" name="selectedVenderName">
        <option></option>
    </select>
</div>
<div class="col-md-3" style="margin-left: -5%;">
    <h4>Inquiry Name</h4>
    <input type="text" class="form-control" name="inquiryName">
    <br>
    <button type="button" onclick="deleteRevision('inquiry');" class="btn btn-default"
    style="width:67%;margin-top:10%;">Delete Inquiry
</button>
</div>
<div class="col-md-3" style="margin-left: -5%;">
    <input type="hidden" name="boqNameList" value="${boqNameList}">
    <input type="hidden" name="quotationNamesList" value="${quotationNamesList}">
    <br>
    <button type="button" onclick="createInquiry();" class="btn btn-default">Generate &
        Send Inquiry
    </button>
    <br>
    <br>
    <button type="button" onclick="downloadInquiry('inquiry');" class="btn btn-default"
    style="width:67%;margin-top:7.5%;">Download Inquiry
</button>
</div>
</div>
</div>
<div class="tab-pane fade pl-5" id="vtab4" role="tabpanel">
    <ul class="nav nav-tabs style-1" role="tablist" id="sheetListtableContentPOSec">
    </ul>
    <div class="tab-content" id="tableContentPOSec">
    </div>
    <div class="row" style="margin-top: 2%;margin-bottom: 2%;">
        <div class="col-md-3" style="margin-left:5%" ;>
            <h4>Inquiry List</h4>
            <select class="form-control offerRevisionSection"
            onchange="download($(this).val(),'tableContentPOSec');">
            <option></option>
        </select>
    </div>
    <div class="col-md-3 ">
        <h4>PO List:</h4>
        <select class="form-control" onchange="downloadPO($('#poList').val());" id="poList">
            <option></option>
        </select>

    </div>
    <div class="col-md-3" style="margin-top: 1.5%;">
        <button type="button" onclick="generatePO();" class="btn btn-default">Generate PO
        </button>
    </div>
</div>
</div>
<div class="tab-pane fade pl-5" id="vtab5" role="tabpanel">
    <div class="row" style="margin-top: 2%;margin-bottom: 2%;">
        <div class="col-md-3">
            <h4>TaxInvoice List</h4>
            <select class="form-control" onchange="downloadInvoice($('#taxInvoiceList').val());"
            id="taxInvoiceList">
            <option></option>
        </select>
    </div>
    <div class="col-md-3">
        <button type="button" class="btn btn-default" data-toggle="collapse"
        data-target="#payDetails" onclick="closeOther('payDetails');">Received
        Payment Details
    </button>
</div>
<div class="col-md-3">
    <button type="button" class="btn btn-default" data-toggle="collapse"
    data-target="#createProjectModal5"
    onclick="closeOther('createProjectModal5');">Add Payment Details
</button>
</div>
<div class="col-md-3">
    <button type="button" class="btn btn-default" data-toggle="collapse"
    data-target="#generateInvoice" id="generateInvoiceFrNInv"
    onclick="closeOther('generateInvoice');">Generate Invoice
</button>
</div>
</div>
<div class="separator clearfix"></div>
<div class="collapse" id="createProjectModal5">
    <div class="row pl-5 mb-5" style="margin-top: 1.5%;">
        <div class="col-md-4" style="margin-left: -4%;">
            <label class="" style="margin-top: 1%;">Received Amount</label>
            <input class="form-control" style="" type="text" id="" name="receivedAmount"
            value="" project="">
        </div>
        <div class="col-md-4" style="margin-left: -5%;">
            <label class="" style="margin-top: 1%;">Payment Mode</label>
            <input class="form-control" style="" type="text" id="" name="paymentMode"
            value="" project="">
        </div>
        <div class="col-md-4" style="margin-left: -4%;">
            <label class="" style="margin-top: 1%;"></label>
            <input type="button" class="btn btn-default" onClick="addPayDetails();"
            value="Add">
        </div>
    </div>
</div>
<div class="collapse" id="payDetails">
    <div class="col-md-12 pl-5" style="margin-top: 1.5%;">
        <table id="payDetailsSection" class="table table-striped table-colored border border-info">
            <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Amount Received</th>
                    <th>Payment Method</th>
                    <th>Date Received</th>
                </tr>
            </thead>
            <tbody id="paymentDetailsBody">
                ${paymentDetails}
                <tr>
                  <td></td>
                  <td></td>
                  <td>Pending Amount</td>
                  <td>Total Amount</td>
              </tr>
              <tr>
                  <td class="invoiceList" style="width:40%;">
                    <div class="card">
                      <p class="card-text p-3">${invoiceList}</p>
                  </div>
              </td>
              <td></td>
              <td class="pendingAmount">${pendingAmount}</td>
              <td class="totalAmount">${totalAmount}</td>
          </tr>
      </tbody>
  </table>
</div>
</div>
<div class="collapse" id="generateInvoice">
    <div class="row ml-3">
        <div class="col-md-12 pr-5">
            <form action="generateInvoice" id="updateInventory" method="POST">
                <dir class="row">
                    <table class="table table-colored">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Product</th>
                                <th>MOC</th>
                                <th>Manufacture Type</th>
                                <th>Class/Grade</th>
                                <th>Material Specs</th>
                                <th>Standard Type</th>
                                <th>Ends</th>
                                <th>Size</th>
                                <th>Purchase Rate</th>
                                <th>Received Quantity</th>
                                <th>Location</th>
                                <th>Received Date</th>
                            </tr>
                        </thead>
                        <tbody id="generateInvoiceTable">
                        </tbody>
                    </table>
                </dir>
                <div class="collapse border border-info" id="invoice"
                style="padding-left:3%;">
                <label><h3></h3></label>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label>Contact Name</label>
                        <input type="text" class="form-control" name="contactName"
                        value="${contactName}">
                    </div>
                    <div class="form-group col-md-3">
                        <label>Mobile No</label>
                        <input type="text" class="form-control" name="mobileNo"
                        value="${mobileNo}">
                    </div>
                    <div class="form-group col-md-3">
                        <label>Addressed To</label>
                        <input type="text" class="form-control" name="addressedto1"
                        value="${addressedTo}">
                    </div>
                    <div class="form-group col-md-3">
                        <label>Order Date</label>
                        <input type="text" class="form-control" name="orderDate">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label>Email</label>
                        <input type="text" class="form-control" name="emailAddress"
                        value="${emailAddress}">
                    </div>
                    <div class="form-group col-md-3">
                        <label>Invoice Type</label>
                        <select class="form-control" name="invoiceType"
                        id="invoiceType">
                        <option></option>
                        <option value="Supply">Supply</option>
                        <option value="Labour">Labour</option>
                        <option value="Supply&amp;Labour">Supply&amp;Labour</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label>Hsn/Sac</label>
                    <input type="text" class="form-control" name="hsnOrSac">
                </div>
                <div class="form-group col-md-3">
                    <br/>

                </div>
                <input type="hidden" name="orderNo" value="">
            </div>

        </div>
        <div class="row" style="margin-left: 0%;">
            <div class="col-md-3 ">
                <div class="ph-20 feature-box text-center">
                    <br>
                    <button type="button" onclick="hideOthers('invoice')"
                    data-toggle="collapse" data-target="#invoice"
                    class="btn btn-default">Invoice Details
                </button>
            </div>
        </div>
        <div class="col-md-3">
            <div class="ph-20 feature-box text-center">
                <br>
                <button type="button" id="updateButton"
                onclick="generateInvoice();" class="btn btn-default">
                Generate Invoice
            </button>
        </div>
    </div>
</div>
</form>
</div>
</div>
</div>
</div>
<div class="tab-pane fade" id="vtab6" role="tabpanel">
    <div class="row pl-5" style="margin-top: 2%;margin-bottom: 2%;">
        <div class="col-md-3" align="center">
            <br>
            <button type="button" class="btn btn-default" onClick="hideOthers1('aitem')"
            style="margin-left:8%;" data-toggle="collapse"
            data-target="#assignedInventory">Show Assigned Item
        </button>
    </div>
    <div class="col-md-3" align="center">
        <br>
        <button type="button" class="btn btn-default" onClick="hideOthers1('citem')"
        style="margin-left:6%;" data-toggle="collapse"
        data-target="#consumedInventory">Show Consumed Item
    </button>
</div>
  <div class="col-md-3" align="center">
                                    <br>
                                    <button type="button" class="btn btn-default" onClick="hideOthers1('mitem')"
                                            style="margin-left:6%;" data-toggle="collapse"
                                            data-target="#receivedInventory">Show Received Item
                                    </button>
                                </div>
</div>
<div class="container collapse" id="aitem" style="max-width:98%;" id="assignedInventory">
    <div class="col-md-12 ">
        <div class="table-responsive">
            <table class="table table-colored assignedInventorySec border border-info">
                <thead>
                    <tr>
                        <th></th>
                        <th>Product</th>
                        <th>MOC</th>
                        <th>Manufacture Type</th>
                        <th>Class/Grade</th>
                        <th>Material Specs</th>
                        <th>Standard Type</th>
                        <th>Ends</th>
                        <th>Size</th>
                        <th>Quantity</th>
                        <th>Purchase Rate</th>
                        <th>Project</th>
                        <th>Location</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="tableContentDetails">
                    ${assignedInventory}
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="container collapse" id="citem" style="max-width:98%;" id="consumedInventory">
    <div class="col-md-12 ">
        <div class="table-responsive">
            <table class="table table-colored border border-info">
                <thead>
                    <tr>
                        <th></th>
                        <th>Product</th>
                        <th>MOC</th>
                        <th>Manufacture Type</th>
                        <th>Class/Grade</th>
                        <th>Material Specs</th>
                        <th>Standard Type</th>
                        <th>Ends</th>
                        <th>Size</th>
                        <th>Quantity</th>
                        <th>Purchase Rate</th>
                        <th>Project</th>
                        <th>Location</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    ${consumedInventory}
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="container collapse" id="mitem" style="max-width:98%;" id="receivedInventory">
                                <div class="col-md-12 ">
                                    <div class="table-responsive">
                                        <table class="table table-colored border border-info">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th>Item</th>
                                                <th>Material</th>
                                                <th>Type</th>
                                                 <th>Class/Schedule</th>
                                                <th>Manifacturing Method</th>
                                                <th>Ends</th>
                                                <th>Size</th>
                                                <th>Quantity</th>
                                                <th>Project</th>
                                                <th>Location</th>
                                                <th>Challan No</th>
                                                <th>Consignee</th>
                                                <th>Received Date</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            ${receivedInventory}
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
</div>
</div>
</div>
</div>
</section>
</section>
</div>
<!-- main-container end -->
<!-- footer start (Add "dark" class to #footer in order to enable dark footer) -->
<!-- ================ -->
<footer id="footer" class="clearfix ">
    <div class="subfooter">
        <div class="container">
            <div class="subfooter-inner">
                <div class="row">
                    <div class="col-md-12">
                        <p class="text-center">Powered By Social Angels Digital Solution Pvt Ltd.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

<form action="generateOrderForm" id="generateOffer" method="POST">
</form>

<div class="modal-backdrop1" style="display:none;">
    <img src="images/img/loading.gif" style="padding-left: 45%;padding-bottom: 45%;">
</div>

<script src="plugins/jquery.min.js"></script>

<script src="plugins/jquery.blockUI.js"></script>

<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Magnific Popup javascript -->
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<!-- Appear javascript -->
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<!-- Count To javascript -->
<script src="plugins/countTo/jquery.countTo.js"></script>
<!-- Slick carousel javascript -->
<script src="plugins/slick/slick.min.js"></script>
<!-- Initialization of Plugins -->
<script src="js/template.js"></script>
<!-- Custom Scripts -->
<script src="js/custom.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/updateProject.js"></script>
</body>
</html>
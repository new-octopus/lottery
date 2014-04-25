<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>彩票接口测试页面</title>
  <link rel='stylesheet' href='assets/css/style.css'/>
  <script type="text/javascript" src="assets/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript">
      $(function () {
          $("#J_api").change(function () {
        	  switch($(this).val()) {
        	  case "lottery/ensure":
        	  case "lottery/exchange":
        		  $("#t_lottery_no").attr("style","display");
        		  $("#i_lottery_no").attr("style","display");
        		  $("#J_lottery_no").val("VvTGOrK9vRbaQmq+5ZUacYacK/qgcbxx");
        		  $("#J_sign").val("490F19C982727DDFB093443E96F69D74876E5A2F");
        		  break;
        	  default:
        		  $("#t_lottery_no").attr("style","display:none");
        		  $("#i_lottery_no").attr("style","display:none");
        		  $("#J_lottery_no").val("");
        		  $("#J_sign").val("E19741A0D31FD3146F212CF30F0E9DE6CBB87DA5");
        	  }
        	  $("#J_url").val("");
        	  $("#J_result").val("");
          });
          $("#J_call").click(function() {
        	  var queryArgs;
        	  if ("lottery/get"==$("#J_api").val()) {
        		  queryArgs = "sign="+$("#J_sign").val();
        	  } else {
        		  queryArgs = $("#J_form input[type=text]").serialize();
        	  }
              $("#J_url").val($("#J_api").val()+"?"+queryArgs);
              $.ajax({
                  type: "GET",
                  url: $("#J_api").val(),
                  data: queryArgs,
                  success: function(data){
                      $("#J_result").val(JSON.stringify(data));
                  }
              });
          });
          $("#J_open").click(function(){
              var url = $("#J_url").val();
              if (url) {
                  window.open(url);
              }
          });
      });
  </script>
</head>
<body>
<div class="main-wrap docu-bg">
    <div class="pages-bg"></div>
    <div class="page-main ">
        <div id="J_form" style="margin-top:30px; margin-left:60px">
            <div class="bd">
                <div class="form-chk" style="padding-bottom: 0;">
                    <ul>
						<li><h1>彩票接口测试</h1></li>
                        <li>
                            <div class="tit"><label for="J_api">API名称：</label></div>
                            <div class="f-part">
                                <select id="J_api" name="api" class="output-txt" style="width:374px;">
                                            <option value="lottery/get" selected>lottery/get</option>
                                             <option value="lottery/ensure">lottery/ensure</option>
                                             <option value="lottery/exchange">lottery/exchange</option>
                                </select>
                            </div>
                        </li>
                        <li>参数列表：</li>
                        <li>
                            <div class="tit">
                                <label for="J_sign">sign</label>
                            </div>
                            <div class="f-part">
                                <input id="J_sign" name="sign" class="input-plain itxt-l"
                                       value="E19741A0D31FD3146F212CF30F0E9DE6CBB87DA5"  type="text" >
                                <span>*</span>
                            </div>
                            <div id="t_lottery_no" class="tit"  style="display: none;">
                                <label for="J_lottery_no">lottery_no</label>
                            </div>
                            <div id="i_lottery_no" class="f-part" style="display: none;">
                                <input id="J_lottery_no" name="lottery_no" class="input-plain itxt-l"
                                       type="text" >
                                <span>*</span>
                            </div>
                        </li>
                    </ul>
                    <div class="btn-box apioper">
                        <ul>
                            <li class="btn">
                                <span class="medi-btn"><input class="btn-txt" value="调用接口" id="J_call"
                                                              type="button"></span>
                        </ul>
                    </div>
                </div>
            </div>
            <hr style="width:800px;margin-left: 0px;">
            <div class="bd">

                <div class="form-chk">
                    <ul>
                        <li>
                            <div class="tit"><label for="J_url">URL地址参数：</label></div>
                            <div class="f-part"><textarea cols="" rows="4" class="tar-plain" style="width:600px"
                                                          id="J_url" readonly="readonly"></textarea></div>
                        </li>
                        <li>
                            <div class="tit"><label for="J_result">返回内容：</label></div>
                            <div class="f-part"><textarea id="J_result" class="tar-plain"
                                                          style="width:600px;height:300px;overflow:auto"
                                                          readonly="readonly"></textarea></div>
                        </li>
                    </ul>

                    <div class="btn-box">
                        <span class="medi-btn"><input class="btn-txt" value="新窗口打开" id="J_open" type="button"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
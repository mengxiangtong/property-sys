$().ready(function(){
	$("#menu-zhanghu").addClass("active");
	$("#table-zhuhu").DataTable({
		"columns":[//定义要显示的列名
					{ data: 'id',sTitle:"",
						render: function(id) {
			        		var cell = arguments[3];
			        		var index = (cell.settings._iDisplayStart+cell.row+1);
							var str = "<input class='tcheckbox' id='d"+index+"' name='slecteOrder' data-uid='"+id+"' type=checkbox> "
							   +"<label for='d"+index+"'>"+index+"</label>";
							return str;
			        	}
					},
					{data : 'userName',sTitle : "用户名"},
					{data : 'roleName',sTitle : "角色类型"},
					{data : 'email',sTitle : "邮箱"}, 
					{data : 'tel',sTitle : "手机号码"},
					{data : 'balance',sTitle : "账户余额"},
					{data : 'registerTime',sTitle : "注册时间"},
					{data : 'lastLoginTime',sTitle : "最后一次登录"},
					{data : 'source',sTitle : "来源"},
					{data : 'status',sTitle : "状态"},
					{data : 'unit',sTitle : "所属单元"}
				],
		"order": [[ 1, 'asc' ]],
		"scrollX": true,//水平滚动条
		"scrollXInner":"120%",
		"processing": true,
	    "serverSide": true,
	    "bAutoWidth": false,//自适应宽度
	    "aLengthMenu": [5,10, 20, 30, 50],//定义每页显示数据数量
	    "fnServerData":function(n,params,fnCallback,table){//向后台请求列表数据
	    	params.push({name:"sSearch",value:params[5].value.value});
	    	$.ajax({
	    		url:"/property-sys/property-sys/userAction!listUsersByParams.action",
	    		type:"post",
	    		dataType:"json",
	    		data:{dataTableParams:JSON.stringify(params)},
	    		success:function(d){
	   			fnCallback(d.msg);
	   		}
	   	});
	   },
		"sort": false,
		"language": {
			"search":"快速搜索",                    //汉化   
		    "lengthMenu": "每页显示 _MENU_ 条记录",   
		    "zeroRecords": "没有查询到相关数据",
		    "info" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
		    "infoEmtpy": "没有数据",   
		    "processing": "正在加载数据...",   
		    "paginate": {   
		        "first": "首页",   
		        "previous": "上一页",
		        "next": "下一页",   
		        "last": "尾页"  
			} 
		}
	});
	
	//删除用户
	$("#btn-deleteZhuhu").on("click.delete",function(){
		var ListId = controls.getCheckedId("#table-zhuhu");
		if(ListId.length>0){
			$.W.alert("确定删除"+ListId.length+"条记录？",true,function(){
				console.log("参数id数组："+ListId);
				//ajax提交
				$.ajax({
	        		url:"/property-sys/property-sys/userAction!deleteUserByIds.action",
	        		type:"post",
	        		dataType:"json",
	        		data:{ids:ListId.toString()},
	        		success:function(d){
	        			$.W.alert(d.msg,true);
	        			//删除后刷新表格
	        			if(d.success){
	        				//
	        				window.location.reload(true);
	        			}
	        		}
	        	});
			});
		}else{
			$.W.alert("请选中要删除的用户！",true);
		}
	});
	//重置密码
	$("#btn-resetPassword").on("click.delete",function(){
		var userId = controls.getCheckedId("#table-zhuhu");
		if(userId.length>0){
			console.log("参数id数组："+userId);
			if(userId.length>1){
				$.W.alert("一次只能重置一个用户的密码！",true);
			}else{
				$.W.alert("确定要重置密码吗？",function(){
					$.ajax({
		        		url:"/property-sys/property-sys/userAction!resetPassword.action",
		        		type:"post",
		        		dataType:"json",
		        		data:{ids:userId[0]},
		        		success:function(d){
		        			$.W.alert(d.msg,true);
		        			//重置后刷新表格
		        			if(d.success){
		        				window.location.reload(true);
		        			}
		        		}
		        	});
				});
			}
		}else{
			$.W.alert("请选中要重置密码的用户！",true);
		}
	});
});


//

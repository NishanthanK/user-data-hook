





YUI({ lang: 'en' }).use(
		'aui-datepicker',
		function(Y) {
			var datepicker = new Y.DatePicker(
					{
						trigger: '#'+war+'dob',
						mask: '%d/%m/%Y',
						popover: {
							toolbars: {
								header: [[
								          {
								        	  icon:'icon-trash',
								        	  label: 'Clear',
								        	  on: {
								        		  click: function() {
								        			  datepicker.clearSelection();
								        		  }
								        	  }
								          }
								          
								          ]]
							},
							zIndex: 1
						}
					}
			);
			
			
		}
);

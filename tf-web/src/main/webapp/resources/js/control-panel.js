
// ************************************************
// *************** Control Panel JS ***************
// ************************************************


YUI.add('cp-table', function (Y) {

	// ---------------------------------
	// -- Object: Control panel table --
	// ---------------------------------
	Y.CpTable = function(conf) {

		// Initial configuration

		this.conf = conf;

		// Check configuration

		this.checkConf = function() {
			inConf = this.conf;
			var isValidConf = true;

			// inConf must != null
			if (inConf) {

				// Parameters list
				var numPages = inConf.numPages;
				var page = inConf.page;
				var size = inConf.size;
				var dataTableId = inConf.dataTableId;
				var paginationId = inConf.paginationId;
				var listUrl = inConf.listUrl;
				var editUrl = inConf.editUrl;
				var columns = inConf.columns;
				var total = inConf.total;
				var maxPages = inConf.maxPages;
				var offset = inConf.offset;
				var mid = inConf.mid;
				var realPage = inConf.realPage;
				var dataId = inConf.dataId;
				
				// Local variables
				inConf.table = null;
				inConf.pagesText = null;
				inConf.pagination = null;

				// Validations
				if (!dataTableId) { return false; }
				if (!paginationId) { return false; }
				if (!listUrl) { return false; }
				if (!columns) { return false; }
				if (!dataId){ return false; }
				
				// Default values
				if (!editUrl) { inConf.editUrl = ''; }
				if (!numPages) { inConf.numPages = [5, 10, 30, 100]; }
				if (!page) { inConf.page = 1; }
				if (!realPage) { inConf.realPage = 1; }
				if (!size) { inConf.size = inConf.numPages[0]; }
				if (!total) { inConf.total = 1; }
				if (!maxPages) { inConf.maxPages = 5; }
				if (!offset) { inConf.offset = 1; }
				if (!mid) {
					inConf.mid = (inConf.maxPages % 2 == 0) ? 
						inConf.maxPages / 2 : 
						(inConf.maxPages / 2) + 0.5;
				}
				
			}
			else {
				isValidConf = false;
			}


			return isValidConf;
		};

		// Render

		this.render = function() {
			var instance = this;
			
			if (this.checkConf()) {
				
				// If the config is ok, the we'll render the datatable
				instance.createPagination();

			}
		};

		// Create Pagination

		this.createPagination = function() {
			
			var instance = this;
			var localConf = instance.conf;
			
			localConf.pagination = new Y.Pagination({
				page: localConf.page,
				offset: localConf.offset,
				total: localConf.total,
				circular: false,
				strings: {
		          next: '>',
		          prev: '<'
		        },
		        after: {
		        	changeRequest: function(event) {
		        		if (event.state.page > 0) {
		        			localConf.page = event.state.page;
			        		if (localConf.total > localConf.maxPages) {
			        			inConf.realPage = localConf.offset + (localConf.page -1);
			        		}
			        		else {
			        			inConf.realPage = localConf.page;
			        		}
			        		instance.findData(inConf.realPage, localConf.size);	
		        		}
		        	}
		        }
			}).render(localConf.paginationId);
			
		};

		// Ajax call

		this.findData = function(inPage, inSize, fromDrop) {

			var instance = this;
			var localConf = instance.conf;

			Y.io.request(
				localConf.listUrl +'/' + (inPage - 1) + '/' + inSize,
				{
					dataType: 'json',
					on: {
						success: function() {
							
							resData = this.get('responseData');
							
							localConf.pagesText = resData.pageText;
							localConf.total = resData.totalPages;
							localConf.size = inSize;
							
							// Max pages
							if (localConf.total > localConf.maxPages) {
								
								localConf.pagination.set('total', localConf.maxPages);
								var engPags = localConf.total - (localConf.mid - 1);
								
								if (localConf.realPage > localConf.mid && localConf.realPage <= engPags) {

									localConf.offset = inConf.realPage - (localConf.mid - 1);
									localConf.pagination.set('offset', localConf.offset);
									localConf.page = localConf.mid;
									localConf.pagination.set('page', localConf.mid);
									
								}
								else if (localConf.realPage > localConf.mid && localConf.realPage > engPags) {

									localConf.offset = engPags - (localConf.mid - 1);
									localConf.page = (localConf.realPage - engPags) + (localConf.mid);
									localConf.pagination.set('offset', localConf.offset);
									localConf.pagination.set('page', localConf.page);
								}
								else {
									
									localConf.offset = 1;
									localConf.page = localConf.realPage;
									localConf.pagination.set('offset', localConf.offset);
									localConf.pagination.set('page', localConf.page);
								}
								

								if (fromDrop) {
									instance.firstLastPage();
								}
								
								// Set selected page
								if (localConf.table) {
									
									instance.noPageSelected();
									
									if (!fromDrop) {
										instance.firstLastPage();	
									}
									
									Y.all('ul.pagination-content li').item(localConf.page + 1).addClass('active');
									
								}
								
							}
							else {
								localConf.pagination.set('total', localConf.total);
								localConf.pagination.set('page', localConf.page);
								localConf.pagination.set('offset', localConf.offset);
							}
							
							// Table creation
							if (!localConf.table) {
								instance.firstLastPage();
								instance.createTable(resData.data);
								localConf.table.render(localConf.dataTableId);
							}
							else {
								localConf.table.set('data', resData.data);
								Y.one('#cp-table-pages-text').html(localConf.pagesText);
							}
							
							

							// Selected pages (First or last page)
							if (inConf.realPage === 1 && localConf.offset === 1) {
								
								instance.noPageSelected();
								
								var ctrlLi = Y.all('ul.pagination-content li');
								
								if (localConf.total > 1) {
									ctrlLi.item(0).addClass('disabled');
								}
								else {
									Y.all('ul.pagination-content li.pagination-control').addClass('disabled');
								}
								
								ctrlLi.item(1).addClass('active');
								if (localConf.total > localConf.maxPages) {
									ctrlLi.item(2).addClass('active');
								}
								
							}
							else if (localConf.total > localConf.maxPages && localConf.realPage === localConf.total) {
								var ctrlLi = Y.all('ul.pagination-content li');
								ctrlLi.item(ctrlLi.size() - 1).addClass('disabled');
								ctrlLi.item(ctrlLi.size() - 2).addClass('disabled');
								ctrlLi.item(ctrlLi.size() - 3).addClass('active');
							}
							
						}
					}
				}
			);

		};

		// Data table creation
				
        this.createTable  = function(inData) {
        	
        	var instance = this;
			var localConf = instance.conf;

			// Tool Column
			
			if (localConf.editUrl) {
				
				var toolColumn = {
					label: '&nbsp;',
					allowHTML: true,
					nodeFormatter: function (o) {

						var eUrl = localConf.editUrl + '/' + o.data[localConf.dataId];
						var editButton =  Y.Node.create(
							'<a class="btn edit-entry" href="' + eUrl +'">' +
							'	<i class="icon-file"></i>'+
							'	Edit' +
							'</a>');
						
						editButton.on('click', function(e){
							e.preventDefault();
							instance.callModal(eUrl);
						});
						
						o.cell.insert(editButton);

						return true;
					}
				};
				
				localConf.columns.push(toolColumn);
			}
			
			// Table creation
			
			localConf.table = new Y.DataTable({

				columns: localConf.columns,
				data: inData
				
			});
        	
			instance.createRowsPerPage();
			instance.setPagesText();
		};

		// Rows per page

		this.createRowsPerPage = function() {

			var instance = this;
			var localConf = instance.conf;
			
			var opts = '';
			for (var i = 0; i < localConf.numPages.length; i++) {
				var vl = localConf.numPages[i];
				opts = opts + '<option value="' + vl + '">' + vl + '</option>';
			};

			var dropDown = 
			'<select class="form-control input-sm page-drop-down" id="cp-table-drop-down">' + 
			'	' + opts +
			'</select>';

			// Node creation
			var dropDownPages = Y.Node.create(dropDown);
			dropDownPages.on('change', function(event){
				localConf.size = localConf.numPages[event.target.get('selectedIndex')];
				localConf.offset = 1;
				localConf.page = 1;
				localConf.realPage = 1;
				instance.findData(1, localConf.size, true);
			});
			var pagLocation = localConf.paginationId + ' .pagination';
			Y.one(pagLocation).placeAfter(dropDownPages);

		};

		// Pages text

		this.setPagesText = function() {
			
			var instance = this;
			var localConf = instance.conf;
			
			var inNode = Y.one('#cp-table-drop-down');
			var strText = 
				'<div class="pages-text" id="cp-table-pages-text">' 
					+ localConf.pagesText + 
				'</div>';
			
			inNode.placeAfter(strText);
			
		};
		
		// First & last page
		
		this.firstLastPage = function() {
			
			var instance = this;
			var localConf = instance.conf;

			var ltr = Y.one('ul.pagination-content #cp-first-page');
			var gtr = Y.one('ul.pagination-content #cp-last-page');
			
			if (ltr && gtr) {
				ltr.remove(true);
				gtr.remove(true);
			}
			
			if (localConf.total > localConf.maxPages) {
			
				var lt = Y.Node.create('<li id="cp-first-page"><a href="#">&lt;&lt;</a></li>');
				var gt = Y.Node.create('<li id="cp-last-page"><a href="#">&gt;&gt;</a></li>');
				
				lt.on('click', function(){
					localConf.offset = 1;
					localConf.page = 1;
					localConf.realPage = 1;
					instance.findData(1, localConf.size);
				});
				
				gt.on('click', function(){
					localConf.realPage = localConf.total;
					instance.findData(localConf.realPage, localConf.size);
				});
				
				Y.all('ul.pagination-content li').item(0).placeBefore(lt);
				Y.one('ul.pagination-content').insert(gt);
				
			}
		};
		
		// Set no page selected
		
		this.noPageSelected = function() {
			
			Y.all('ul.pagination-content li.active').removeClass('active');
			Y.all('ul.pagination-content li.disabled').removeClass('disabled');
			
		};
		
		// Call modal 
		this.callModal = function(url) {
			
			Y.io.request(url, {
					dataType: 'text/html',
					on: {
						success: function() {
							var html = this.get('responseData');
							var windowModal = new Y.Modal({
								bodyContent: html,
								headerContent: 'Edit',
						        centered: true,
						        modal: true
							}).render();
							
							Y.one('#' + windowModal.get('id')).all('script').each(function(o) {
								eval(o.get('text'));
							});

						}
					}
				}
			);
			
		};
		
	};
},'0.0.1', {
	requires: ['aui-datatable', 'aui-pagination', 'aui-io-request', 'aui-node', 'aui-modal']
});

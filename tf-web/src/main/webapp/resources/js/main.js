
// ************************************************
// *************** Control Panel JS ***************
// ************************************************

// Object: Control panel table

function CpTable(conf) {

	// Initial configuration

	this.conf = conf;

	// Check configuration

	this.checkConf = function() {
		inConf = this.conf;
		var isValidConf = true;

		// inConf must != null
		if (inConf) {

			if (!inConf.msg) { isValidConf = false; }

		}
		else {
			isValidConf = false;
		}


		return isValidConf;
	};

	// Render

	this.render = function() {
		var localConf = this.conf;
		console.log(localConf.msg);
	};

}

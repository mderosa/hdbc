
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperimentDlg = Ext.extend(Ext.Window, {
	
	initComponent: function(config) {
		Ext.apply(this, {
			id: 'wndnewexpmnt'
			,title: 'New Experiment'
			,autoHeight: true
			,width: 500
			,items: [{
				xtype: 'newexperimentform'
			}]
		});
		Hippo.dialog.NewExperimentDlg.superclass.initComponent.apply(this, arguments);

	}
});
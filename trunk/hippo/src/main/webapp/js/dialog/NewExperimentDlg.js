
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperimentDlg = Ext.extend(Ext.Window, {
	id: 'wndnewexpmnt'
	,title: 'New Experiment'
	,autoHeight: true
	,width: 500
	
	,initComponent: function(config) {
		Ext.apply(this, {
			items: [{
				xtype: 'newexperimentform'
			}]
		});
		Hippo.dialog.NewExperimentDlg.superclass.initComponent.apply(this, arguments);

	}
});
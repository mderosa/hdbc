
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperimentDlg = Ext.extend(Ext.Window, {
	id: 'newexperimentdlg'
	,title: 'New Experiment'
	,autoHeight: true
	,width: 500
	
	,initComponent: function(config) {
		Ext.apply(this, {
			items: [{
				xtype: 'form.NewExperimentForm'
			}]
		});
		Hippo.dialog.NewExperimentDlg.superclass.initComponent.apply(this, arguments);
	}
	
});
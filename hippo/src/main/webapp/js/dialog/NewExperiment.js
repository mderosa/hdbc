
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperiment = new Ext.Window({
	id: 'wndnewexperiment'
	,title: 'New Experiment'
	,items: [{
		xtype: 'form'
		,items: [{
			fieldLabel: 'title'
			,xtype: 'textfield'
		}
		,{
			fieldLabel: 'purpose'
			,xtype: 'textarea'
		}]
	}]
});
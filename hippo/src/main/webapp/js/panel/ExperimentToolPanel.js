
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentToolPanel = Ext.extend(Ext.Toolbar, {
	
	initComponent: function() {

		Ext.apply(this, {
			id: 'toolbar'
			,items: [{
				text: 'Hippo'
				,xtype: 'tbtext'
			}
		   ,{
				xtype: 'tbseparator'
			}
		   ,{
				id: 'newExperiment'
				,text: 'New*'
				,xtype: 'tbbutton'
				,tooltip: 'create a new experiment'
				,style: 'border: solid 1px #AAA'
				,listeners: {
					click: {
						fn: function(e, t) {
							if (!Ext.getCmp('newexperimentdlg')) {
								new Hippo.dialog.NewExperimentDlg().show();
							}
						},
						scope: this
					}
				}
			}]
		});
		
		Hippo.panel.ExperimentToolPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('panel.ExperimentToolPanel', Hippo.panel.ExperimentToolPanel);

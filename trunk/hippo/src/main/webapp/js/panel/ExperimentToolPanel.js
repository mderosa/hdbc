
Ext.namespace('Hippo.panel');

Hippo.panel.ExperimentToolPanel = Ext.extend(Ext.Toolbar, {
	
	initComponent: function() {
		Ext.apply(this, {
			id: 'toolbar'
			,items: [{
				text: 'New*'
				,xtype: 'tbbutton'
				,tooltip: 'create a new experiment'
				,style: 'border: solid 1px #AAA'
			}]
		});
		
		Hippo.panel.ExperimentToolPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimenttoolpanel', Hippo.panel.ExperimentToolPanel);

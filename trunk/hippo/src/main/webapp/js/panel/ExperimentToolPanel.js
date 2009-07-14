
Ext.namespace('Hippo');

Hippo.ExperimentToolPanel = Ext.extend(Ext.Toolbar, {
	
	initComponent: function() {
		Ext.apply(this, {
			items: [{
				text: 'New*'
				,xtype: 'tbbutton'
				,tooltip: 'create a new experiment'
				,style: 'border: solid 1px #AAA'
			}]
		});
		
		Hippo.ExperimentToolPanel.superclass.initComponent.apply(this, arguments);
	}

});

Ext.reg('experimenttoolpanel', Hippo.ExperimentToolPanel);


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
							alert('button');
							this.fireEvent('newexperiment');
						},
						scope: this
					}
				}
			}]
		});
		
		Hippo.panel.ExperimentToolPanel.superclass.initComponent.apply(this, arguments);
		
		this.addEvents('newexperiment');
	}

});

Ext.reg('experimenttoolpanel', Hippo.panel.ExperimentToolPanel);

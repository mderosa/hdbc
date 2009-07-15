
Ext.namespace('Hippo');

Hippo.HippoViewport = Ext.extend(Ext.Viewport, {
	layout: 'border',
	getToolPanel: function() {
		var toolPanel = this.getComponent(0);
		if (toolPanel == null || toolPanel.xtype != 'experimenttoolpanel') {
			throw new Error('the component has not been initialized or is misconfigured');
		}
		return toolPanel;
	},
	initComponent: function() {
		Ext.apply(this, {
			id: 'viewport'
			
			,items: [{
				region: 'north'
				,height: 30
				,xtype: 'experimenttoolpanel'
			}
			,{
				region: 'west',
				collapsible: true,
				width: 300,
				minWidth: 200,
				maxWidth: 400,
				autoScroll: true,
				split: true,
				layout: 'accordion',
				items: [{
					title: 'Experiments'
					,xtype: 'experimentlistpanel'
				}
				,{
					title: 'Settings'
					,xtype: 'experimentsettingspanel'
				}]
			},
			{
				region: 'center'
				,xtype: 'experimenttabpanel'
			}]
		});
		
		
		Hippo.HippoViewport.superclass.initComponent.apply(this, arguments);
		
		this.relayEvents(this.getToolPanel(), ['newexperiment']);
		this.on('newexperiment', function(){
				var dlg = new Hippo.dialog.NewExperiment();
				dlg.show();
			}, this);
	}
	
});

Ext.reg('hippoviewport', Hippo.HippoViewport);




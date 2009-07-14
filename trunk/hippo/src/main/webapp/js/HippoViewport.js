
Ext.namespace('Hippo');

Hippo.HippoViewport = Ext.extend(Ext.Viewport, {
	layout: 'border',
	
	initComponent: function() {
		Ext.apply(this, {
			
			items: [{
				title: 'Hippo'
				,region: 'north'
				,height: 75
				,items: [{
					xtype: 'experimenttoolpanel'
				}]
			},
			{
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
	}
	
});

Ext.reg('hippoviewport', Hippo.HippoViewport);




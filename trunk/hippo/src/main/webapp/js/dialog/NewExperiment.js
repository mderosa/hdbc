
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperiment = Ext.extend(Ext.Window, {
	
	onTextFieldClick: function() {
		console.log('begin on text field click');
		var exists = function(id) {
			return (Ext.get(id).getValue().length > 0);
		}
		var btnCreate = this.getComponent(0);
		btnCreate.createButtonActive(exists('title') && exists('purpose'));
	}
	,initComponent: function(config) {
		Ext.apply(this, {
			id: 'wndnewexpmnt'
			,title: 'New Experiment'
			,autoHeight: true
			,width: 500
			,items: [{
				xtype: 'newexperimentform'
			}]
		});
		Hippo.dialog.NewExperiment.superclass.initComponent.apply(this, arguments);
		this.relayEvents(this.getComponent(0), ['newexpformchange']);
		this.on('newexpformchange', this.onTextFieldClick, this);
	}
});
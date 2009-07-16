
Ext.namespace('Hippo.dialog');

Hippo.dialog.NewExperimentDlg = Ext.extend(Ext.Window, {
	
	onTextFieldClick: function() {
		console.log('begin on text field click');
		var hasContent = function(id) {
			return (Ext.get(id).getValue().length > 0);
		}
		var form = this.getComponent(0);
		form.createButtonActive(hasContent('title') && hasContent('purpose'));
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
		Hippo.dialog.NewExperimentDlg.superclass.initComponent.apply(this, arguments);
		this.relayEvents(this.getComponent(0), ['newexpformchange']);
		this.on('newexpformchange', this.onTextFieldClick, this);
	}
});
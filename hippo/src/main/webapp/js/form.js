
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget = 'side';
	
	var body = Ext.getBody();
	body.createChild({
		tag:'h2',
		html:'A Test Form'
	});
	
	Ext.QuickTips.init();
	var form = new Ext.FormPanel({
		labelWidth: 75,
		url: 'index.html',
		frame: true,
		title: 'Simple Form',
		width: 300,
		height: 250,
		defaults: {width: 150},
		defaultType: 'textfield',
		items: [{
			fieldLabel: 'First Name',
			name: 'firstName',
			allowBlank: false,
			invalidText: 'first name is a required field'
		},
		{
			fieldLabel: 'Last Name',
			name: 'lastName',
			vtype: 'alpha',
			vtypeText: 'should be alpha characters'
		},
		{
			fieldLabel: 'Revision',
			name: 'revision',
			xtype: 'numberfield'
		},
		{
			fieldLabel: 'Start Time',
			name: 'startTime',
			minValue: '9:00 AM',
			maxValue: '5:00 PM',
			increment: '60',
			xtype: 'timefield'
		}],
		
		tools: [{
			id: 'pin',
			qtip: 'stick it to me'
		},
		{
			id: 'refresh',
			qtip: 'refresh from data'
		}]
	});
	
	form.render(document.body);
});
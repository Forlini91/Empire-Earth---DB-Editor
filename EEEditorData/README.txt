All lines starting with # are ignored (you put them anywhere for comments or everything you need)
All fields have structure with these parameters:

		<Field number>	<Num bytes>	<Type>	<Extra data (Optional)>	<Name>	<Description>	<Editable>	<Knowledge>	<Color>

Each parameter is separated by a single Tabulation, so don't use Tabulations in Name or Description unless you want to unleash an apocalyspe
 - Field number: Just the number of the field as displayed in the Editor. Actually... THIS NUMBER IS NOT USED. It's just here to help YOU to find the field quickly in the file. Changing it won't have any effect.
 - Num bytes: The number of bytes used by the field. It could be 1 or 4 for numbers, or any number for strings. Negative numbers will cause an error.
 - Type: Type of the field
 - Extra data: LOOK DOWN
 - Name: Name displayed above the field in the editor
 - Description: Description displayed in the popup when you put your cursor above a field
 - Editable: Is the field editable or is it managed by the editor? Values: EDITABLE or BLOCKED
 - Knowledge: Current knowledge about a field
 - Color: color of the text in the GUI. You can specify the name of a color or a custom color with the format #RRGGBB (see ID_LANGUAGE in common.dats as example)
 

If a field is used many times, you can define it in commonds.dat and reuse it everywhere as many times you want.
Pre-made fields in commonds.dat have a little difference: they don't specify a numeric <Field Number> but a string <KEY>, which will be used to recall them.
If you want to call a pre-made field, you can type his key with this syntax:
		<Field number>	@<KEY>

This way you won't need to write again and again and again all the parameters of the field everytime it appears in the files.
You'll find these calls everywhere and you can even define your own pre-made fields in commonds.dat (ensure you don't touch the other ones unless you want to destroy some planets) and recall them everywhere you need.
	Example: 
		4	@UNKNOWN_INT4
	UNKNOWN_INT4 is defined in commonds.dat and reused literally everywhere in all files
	
 
 

 
 
 

Types:
	BOOLEAN: 1 or 4 bytes (not sure about 4 bytes, but it seems so for now...)
	INTEGER: 1 or 4 bytes integer value
	FLOAT: 1 or 4 bytes float value
	STRING: a fixed size string (always take the same space, can't take more, fill unused space with invisible characters)
	DYNAMIC_STRING: a dynamic size string (only use the space it needs, but a STRING_SIZE field must holds the current string size)
	RANGE: select a value from a list of values from X to Y. Used in rare cases
	ENUM: select a value from a list of hardcoded values (for now)
	LANGUAGE: select one of language localizations
	LINK: select a link to another entry
 
Knowledges:
	KNOWN: we know everything about this field
	NEVER_USED: we only know it's always 0 in all entries (maybe it's an unused value)
	NEVER_CHANGE: we only know it's always the same in all entries
	UNKNOWN: we are like Jon Snow
	
Colors:
	BLACK
	DARK_GRAY
	GRAY
	LIGHT_GRAY
	WHITE
	CYAN
	BLUE
	MAGENTA
	PINK
	RED
	ORANGE
	YELLOW
	GREEN
	
Extra data:
This parameter is optional and is only used with specific TYPES:
	If type is RANGE, you must specify the range with the form <FROM>:<TO>. Look at "Family attack type" in dbobjects.dat as example
	If type is ENUM, you must specify an enum name. Currently these names are hardcoded and unknonw unless you look at the code, but there's no reason to change these names anyway
	If type is LINK, you must specify the name of the target dat file and the default ID for the link, with the form <FILENAME>:<DEFAULT ID>
	If type is DYNAMIC_STRING, you must specify the Field Number of the STRING_SIZE field which hold the string size
All other types won't have this parameter at all.
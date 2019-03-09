0	@NAME
1	@SEQ_NUMBER
2	@ID
3	4	INTEGER	Starting epoch	Starting epoch	EDITABLE	KNOWN	BLACK
4	4	INTEGER	Ending epoch	Ending epoch	EDITABLE	KNOWN	BLACK
5	4	LINK	dbevents.dat:0	Event ID	Event ID	EDITABLE	KNOWN	BLACK
6	@UNKNOWN_INT4
7	4	INTEGER	Wood cost	Wood cost	EDITABLE	KNOWN	BLACK
8	4	INTEGER	Stone cost	Stone cost	EDITABLE	KNOWN	BLACK
9	4	INTEGER	<Only Impassable tile object and Invisible capital>	<Only Impassable tile object and Invisible capital>	EDITABLE	KNOWN	BLACK
10	4	INTEGER	Gold cost	Gold cost	EDITABLE	KNOWN	BLACK
11	@UNUSED_INT4
12	4	INTEGER	Iron cost	Iron cost	EDITABLE	KNOWN	BLACK
13	4	INTEGER	Food cost	Food cost	EDITABLE	KNOWN	BLACK
14	4	INTEGER	Build time	Build time	EDITABLE	KNOWN	BLACK
15	4	LINK	dbtechtree.dat:0	Unlocked by tech	Unlocked by tech	EDITABLE	KNOWN	BLACK
16	4	LINK	dbtechtree.dat:0	Unlocked by power	Unlocked by power	EDITABLE	KNOWN	BLACK
17	4	INTEGER	<-1 in Epoch Space, 0 everywhere else>	<-1 in Epoch Space, 0 everywhere else>	EDITABLE	KNOWN	BLACK
18	4	INTEGER	<-1 in Epoch Space, 0 everywhere else>	<-1 in Epoch Space, 0 everywhere else>	EDITABLE	KNOWN	BLACK
19	4	LINK	dbobjects.dat:-1	Object ID	Object ID	EDITABLE	KNOWN	BLACK
20	@ID_BUTTON
21	4	INTEGER	Is object?	Is object?	EDITABLE	KNOWN	BLACK
22	@ID_LANGUAGE
23	4	ENUM	TechType	Tech type	Tech type	EDITABLE	KNOWN	BLACK
24	4	INTEGER	<4 in all Epochs, 0 everywhere else>	<4 in all Epochs, 0 everywhere else>	EDITABLE	KNOWN	BLACK
25	@UNUSED_INT4
26	@UNUSED_INT4
27	4	LINK	dbcalamity.dat:0	Calamity	Calamity	EDITABLE	KNOWN	BLACK
28	4	LINK	dbuihotkey.dat:0	Hotkey ID	Hotkey ID	EDITABLE	KNOWN	BLACK
29	4	INTEGER	<Only Monoteism and Mech Minotaur use this>	<Only Monoteism and Mech Minotaur use this>	EDITABLE	UNKNOWN	BLACK
30	4	INTEGER	<Only Monoteism and Mech Minotaur use this>	<Only Monoteism and Mech Minotaur use this>	EDITABLE	UNKNOWN	BLACK
31	@UNKNOWN_INT4
32	@UNKNOWN_INT4
33	@UNKNOWN_FLOAT
34	@UNKNOWN_FLOAT
35	@UNKNOWN_FLOAT
36	@UNKNOWN_FLOAT
37	1	BOOLEAN	Researchable in editor trigger	Researchable in editor trigger	EDITABLE	KNOWN	BLACK
38	1	BOOLEAN	Only in scenario	Only in scenario	EDITABLE	KNOWN	BLACK
39	1	BOOLEAN	<All powers and power techs use 0>	<All powers and power techs use 0>	EDITABLE	KNOWN	BLACK
40	1	BOOLEAN	<All powers and power techs use 0>	<All powers and power techs use 0>	EDITABLE	KNOWN	BLACK
41	4	INTEGER	Epoch number	Epoch number	EDITABLE	KNOWN	BLACK
42	4	INTEGER	Buildings to advance epoch	Buildings to advance epoch	EDITABLE	KNOWN	BLACK
43	4	INTEGER	Epoch IDs	Epoch IDs	EDITABLE	KNOWN	BLACK
44	4	LINK	dbobjects.dat:0	Last build object	Last build object	BLOCKED	KNOWN	BLACK
45	4	INTEGER	Num of tech builders	Num of tech builders	BLOCKED	KNOWN	BLACK

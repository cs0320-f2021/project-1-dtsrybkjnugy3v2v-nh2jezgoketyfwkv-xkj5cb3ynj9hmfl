# README
## API Component:
    What makes a good API Aggregator? A good API Aggregator is fast and efficient.
    This means that the aggregator is making as few calls as possible to the APIs.

### Design Choices:
    I assumed that I knew what type of data I was parsing, so I wrote three classes with the
    fields that this data would have. I then created data classes that utilized parsed data from the
    apis into the specific class type. My most unique choice was using sets to aggregate data. I utilized primary keys
    to determine if the set already had this data in it.

    Since I used sets, I was able to only make a single call to each api.

## K-D Tree Component:
    A K-D tree is a data structure used to store items such that that they can be sorted and found quickly 
    and accurately. In this case, we are sorting and finding users of 'Rent the Runway' who are most similar 
    to a given user or description.

### Design Choices:
    The nearest neighbors algorithm utilizes the K-D tree to find similar users efficiently. 
    The algorithm starts at the root node and compares the relevant axis of root to the same axis of 
    the target user or to the corresponding target parameter. It then recurses on one of the children 
    of the root and repeats the process until the list of nearest neighbors is populated with the most 
    similar users. 
    
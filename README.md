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

        
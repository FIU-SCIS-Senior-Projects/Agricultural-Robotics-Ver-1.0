from .models import Account


DB_HOST = ["localhost"]
DB_PORT = 27017


def get_db(db_name):
    import pymongo
    DB_HOST = ["localhost"]
    DB_PORT = 27017
    db = pymongo.Connection(DB_HOST, DB_PORT)[db_name]
    return db


def get_mongo_cursor(db_name, collection_name, max_docs=100):
    import pymongo
    db = pymongo.Connection(host=DB_HOST,
                            port=DB_PORT)[db_name]
    collection = db[collection_name]
    cursor = collection.find()
    count = cursor.count
    if callable(count):
        count = count()
    if count >= max_docs:
        cursor = cursor[0:max_docs]
    return cursor


data = [
       ['Week', 'Count', 'Area', 'Items Sold', 'Net Profit'],
       ['2004', 1000, 400, 100, 600],
       ['2005', 1170, 460, 120, 710],
       ['2006', 660, 1120, 50, -460],
       ['2007', 1030, 540, 100, 490],
       ]

candlestick_data = [['Mon', 20, 28, 38, 45],
                    ['Tue', 31, 38, 55, 66],
                    ['Wed', 50, 55, 77, 80],
                    ['Thu', 77, 77, 66, 50],
                    ['Fri', 68, 66, 22, 15]]

treemap_data = [
          ['Location', 'Parent', 'Market trade volume (size)', 'Market increase/decrease (color)'],
          ['Global',    None,                 0,                               0],
          ['America',   'Global',             0,                               0],
          ['Europe',    'Global',             0,                               0],
          ['Asia',      'Global',             0,                               0],
          ['Australia', 'Global',             0,                               0],
          ['Africa',    'Global',             0,                               0],
          ['Brazil',    'America',            11,                              10],
          ['USA',       'America',            52,                              31],
          ['Mexico',    'America',            24,                              12],
          ['Canada',    'America',            16,                              -23],
          ['France',    'Europe',             42,                              -11],
          ['Germany',   'Europe',             31,                              -2],
          ['Sweden',    'Europe',             22,                              -13],
          ['Italy',     'Europe',             17,                              4],
          ['UK',        'Europe',             21,                              -5],
          ['China',     'Asia',               36,                              4],
          ['Japan',     'Asia',               20,                              -12],
          ['India',     'Asia',               40,                              63],
          ['Laos',      'Asia',               4,                               34],
          ['Mongolia',  'Asia',               1,                               -5],
          ['Israel',    'Asia',               12,                              24],
          ['Iran',      'Asia',               18,                              13],
          ['Pakistan',  'Asia',               11,                              -52],
          ['Egypt',     'Africa',             21,                              0],
          ['S. Africa', 'Africa',             30,                              43],
          ['Sudan',     'Africa',             12,                              2],
          ['Congo',     'Africa',             10,                              12],
          ['Zaire',     'Africa',             8,                               10]]


# map_data = [
#     ['Country', 'Value'],
#     ['fo', 0],
#     ['um', 1],
#     ['us', 2],
#     ['jp', 3],
#     ['sc', 4],
#     ['in', 5],
#     ['fr', 6],
#     ['fm', 7],
#     ['cn', 8],
#     ['pt', 9],
#     ['sw', 10],
#     ['sh', 11],
#     ['br', 12],
#     ['ki', 13],
#     ['ph', 14],
#     ['mx', 15],
#     ['es', 16],
#     ['bu', 17],
#     ['mv', 18],
#     ['sp', 19],
#     ['gb', 20],
#     ['gr', 21],
#     ['as', 22],
#     ['dk', 23],
#     ['gl', 24],
#     ['gu', 25],
#     ['mp', 26],
#     ['pr', 27],
#     ['vi', 28],
#     ['ca', 29],
#     ['st', 30],
#     ['cv', 31],
#     ['dm', 32],
#     ['nl', 33],
#     ['jm', 34],
#     ['ws', 35],
#     ['om', 36],
#     ['vc', 37],
#     ['tr', 38],
#     ['bd', 39],
#     ['lc', 40],
#     ['nr', 41],
#     ['no', 42],
#     ['kn', 43],
#     ['bh', 44],
#     ['to', 45],
#     ['fi', 46],
#     ['id', 47],
#     ['mu', 48],
#     ['se', 49],
#     ['tt', 50],
#     ['my', 51],
#     ['pa', 52],
#     ['pw', 53],
#     ['tv', 54],
#     ['mh', 55],
#     ['cl', 56],
#     ['th', 57],
#     ['gd', 58],
#     ['ee', 59],
#     ['ad', 60],
#     ['tw', 61],
#     ['bb', 62],
#     ['it', 63],
#     ['mt', 64],
#     ['vu', 65],
#     ['sg', 66],
#     ['cy', 67],
#     ['lk', 68],
#     ['km', 69],
#     ['fj', 70],
#     ['ru', 71],
#     ['va', 72],
#     ['sm', 73],
#     ['kz', 74],
#     ['az', 75],
#     ['tj', 76],
#     ['ls', 77],
#     ['uz', 78],
#     ['ma', 79],
#     ['co', 80],
#     ['tl', 81],
#     ['tz', 82],
#     ['ar', 83],
#     ['sa', 84],
#     ['pk', 85],
#     ['ye', 86],
#     ['ae', 87],
#     ['ke', 88],
#     ['pe', 89],
#     ['do', 90],
#     ['ht', 91],
#     ['pg', 92],
#     ['ao', 93],
#     ['kh', 94],
#     ['vn', 95],
#     ['mz', 96],
#     ['cr', 97],
#     ['bj', 98],
#     ['ng', 99],
#     ['ir', 100],
#     ['sv', 101],
#     ['sl', 102],
#     ['gw', 103],
#     ['hr', 104],
#     ['bz', 105],
#     ['za', 106],
#     ['cf', 107],
#     ['sd', 108],
#     ['cd', 109],
#     ['kw', 110],
#     ['de', 111],
#     ['be', 112],
#     ['ie', 113],
#     ['kp', 114],
#     ['kr', 115],
#     ['gy', 116],
#     ['hn', 117],
#     ['mm', 118],
#     ['ga', 119],
#     ['gq', 120],
#     ['ni', 121],
#     ['lv', 122],
#     ['ug', 123],
#     ['mw', 124],
#     ['am', 125],
#     ['sx', 126],
#     ['tm', 127],
#     ['zm', 128],
#     ['nc', 129],
#     ['mr', 130],
#     ['dz', 131],
#     ['lt', 132],
#     ['et', 133],
#     ['er', 134],
#     ['gh', 135],
#     ['si', 136],
#     ['gt', 137],
#     ['ba', 138],
#     ['jo', 139],
#     ['sy', 140],
#     ['mc', 141],
#     ['al', 142],
#     ['uy', 143],
#     ['cnm', 144],
#     ['mn', 145],
#     ['rw', 146],
#     ['so', 147],
#     ['bo', 148],
#     ['cm', 149],
#     ['cg', 150],
#     ['eh', 151],
#     ['rs', 152],
#     ['me', 153],
#     ['tg', 154],
#     ['la', 155],
#     ['af', 156],
#     ['ua', 157],
#     ['sk', 158],
#     ['jk', 159],
#     ['bg', 160],
#     ['qa', 161],
#     ['li', 162],
#     ['at', 163],
#     ['sz', 164],
#     ['hu', 165],
#     ['ro', 166],
#     ['ne', 167],
#     ['lu', 168],
#     ['ad', 169],
#     ['ci', 170],
#     ['lr', 171],
#     ['bn', 172],
#     ['iq', 173],
#     ['ge', 174],
#     ['gm', 175],
#     ['ch', 176],
#     ['td', 177],
#     ['kv', 178],
#     ['lb', 179],
#     ['dj', 180],
#     ['bi', 181],
#     ['sr', 182],
#     ['il', 183],
#     ['ml', 184],
#     ['sn', 185],
#     ['gn', 186],
#     ['zw', 187],
#     ['pl', 188],
#     ['mk', 189],
#     ['py', 190],
#     ['by', 191],
#     ['ca', 192],
#     ['bf', 193],
#     ['na', 194],
#     ['ly', 195],
#     ['tn', 196],
#     ['bt', 197],
#     ['md', 198],
#     ['ss', 199],
#     ['bw', 200],
#     ['bs', 201],
#     ['nz', 202],
#     ['cu', 203],
#     ['ec', 204],
#     ['au', 205],
#     ['ve', 206],
#     ['sb', 207],
#     ['mg', 208],
#     ['is', 209],
#     ['eg', 210],
#     ['kg', 211],
#     ['np', 212]
# ]


map_data = [
    ['Country', 'Value'],
    ['fo', 0],
    ['um', 1],
    ['us', 2],
    ['jp', 3],
    ['sc', 4],
    ['in', 5],
    ['fr', 6],
    ['fm', 7],
    ['cn', 8],
    ['pt', 9],
    ['sw', 10],
    ['sh', 11],
    ['br', 12],
    ['ki', 13],
    ['ph', 14],
    ['mx', 15],
    ['es', 16],
    ['bu', 17],
    ['mv', 18],
    ['sp', 19],
    ['gb', 20],
    ['gr', 21],
    ['as', 22],
    ['dk', 23],
    ['gl', 24],
    ['gu', 25],
    ['mp', 26],
    ['pr', 27],
    ['vi', 28],
    ['ca', 29],
    ['st', 30],
    ['cv', 31],
    ['dm', 32],
    ['nl', 33],
    ['jm', 34],
    ['ws', 35],
    ['om', 36],
    ['vc', 37],
    ['tr', 38],
    ['bd', 39],
    ['lc', 40],
    ['nr', 41],
    ['no', 42],
    ['kn', 43],
    ['bh', 44],
    ['to', 45],
    ['fi', 46],
    ['id', 47],
    ['mu', 48],
    ['se', 49],
    ['tt', 50],
    ['my', 51],
    ['pa', 52],
    ['pw', 53],
    ['tv', 54],
    ['mh', 55],
    ['cl', 56],
    ['th', 57],
    ['gd', 58],
    ['ee', 59],
    ['ad', 60],
    ['tw', 61],
    ['bb', 62],
    ['it', 63],
    ['mt', 64],
    ['vu', 65],
    ['sg', 66],
    ['cy', 67],
    ['lk', 68],
    ['km', 69],
    ['fj', 70],
    ['ru', 71],
    ['va', 72],
    ['sm', 73],
    ['kz', 74],
    ['az', 75],
    ['tj', 76],
    ['ls', 77],
    ['uz', 78],
    ['ma', 79],
    ['co', 80],
    ['tl', 81],
    ['tz', 82],
    ['ar', 83],
    ['sa', 84],
    ['pk', 85],
    ['ye', 86],
    ['ae', 87],
    ['ke', 88],
    ['pe', 89],
    ['do', 90],
    ['ht', 91],
    ['pg', 92],
    ['ao', 93],
    ['kh', 94],
    ['vn', 95],
    ['mz', 96],
    ['cr', 97],
    ['bj', 98],
    ['ng', 99]
]


mongo_series_object_1 = [[440, 39],
                         [488, 29.25],
                         [536, 28],
                         [584, 29],
                         [632, 33.25],
                         [728, 28.5],
                         [776, 33.25],
                         [824, 28.5],
                         [872, 31],
                         [920, 30.75],
                         [968, 26.25]]

mongo_series_object_2 = [[400, 4],
                         [488, 0],
                         [536, 20],
                         [584, 8],
                         [632, 2],
                         [680, 36],
                         [728, 0],
                         [776, 0],
                         [824, 0],
                         [872, 4],
                         [920, 1],
                         [968, 0]]

mongo_data = [{'data': mongo_series_object_1, 'label': 'hours'},
              {'data': mongo_series_object_2, 'label': 'hours'}]


def create_demo_accounts():
    Account.objects.all().delete()
    # Create some rows
    Account.objects.create(week="04", count=1000,
                           area=400, farmer="Welch")
    Account.objects.create(week="05", count=1170,
                           area=460, farmer="Jobs")
    Account.objects.create(week="06", count=660,
                           area=1120, farmer="Page")
    Account.objects.create(week="07", count=1030,
                           area=540, farmer="Welch")
    Account.objects.create(week="08", count=2030,
                           area=1540, farmer="Zuck")
    Account.objects.create(week="09", count=2230,
                           area=1840, farmer="Cook")


def create_demo_mongo():
    accounts = get_db("accounts")
    docs = accounts.docs
    docs.drop()

    docs = accounts.docs
    header = data[0]
    data_only = data[1:]
    for row in data_only:
        docs.insert(dict(zip(header, row)))

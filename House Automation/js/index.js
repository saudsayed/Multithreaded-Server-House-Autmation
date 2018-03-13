var houseAutmation;
$( document ).ready(function () {
  houseAutmation = {
    'livingArea': {
      'AC' : {
        'state': 'ON',
        'temp' : '80'
      },
      'light': {
        'state': 'OFF'
      },
      'curtain': {
        'state': 'Open'
      },
      'TV': {
        'state': 'ON'
      }
    },
    'bedroom': {
      'AC' : {
        'state': 'ON',
        'temp' : '75'
      },
      'light': {
        'state': 'OFF'
      },
      'curtain': {
        'state': 'Open'
      }
    },
    'restroom': {
      'light': {
        'state': 'ON'
      },
      'air_freshner': {
        'state': 'ON',
        'time': 10
      }
    },
    'kitchen': {
      'light': {
        'state': 'ON'
      }
    }
  };
});

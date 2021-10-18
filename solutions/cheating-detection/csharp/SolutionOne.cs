using System;
using System.Collections.Generic;
using System.Linq;

namespace CheatingDetection
{
    class SolutionOne
    {
        static void Main(string[] args)
        {

            var T = Int32.Parse(Console.ReadLine());
            var P = Int32.Parse(Console.ReadLine());

            var totalPlayers = 100;
            var totalQuestions = 10000;

            for (int Ti = 0; Ti < T; Ti++)
            {

                var results = new int[totalPlayers, totalQuestions];
                var questions = new Question[totalQuestions];

                for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++)
                {

                    var answers = Console.ReadLine().ToCharArray();

                    for (int questionIndex = 0; questionIndex < totalQuestions; questionIndex++)
                    {

                        if (questions[questionIndex] == null)
                        {
                            questions[questionIndex] = new Question();
                            questions[questionIndex].ID = questionIndex;
                        }

                        if (answers[questionIndex].Equals('1'))
                        {
                            questions[questionIndex].count += 1;
                        }

                        results[playerIndex, questionIndex] = Int32.Parse(answers[questionIndex].ToString());

                    }

                }

                Array.Sort(questions);

                var ranks = new int[totalQuestions];

                for (int rank = 0; rank < totalQuestions; rank++)
                {
                    var question = questions[rank];
                    ranks[question.ID] = rank;
                }

                var playersMeanRanks = new double[totalPlayers];

                for (int playerIndex = 0; playerIndex < totalPlayers; playerIndex++)
                {

                    var values = new List<int>();

                    for (int questionIndex = 0; questionIndex < totalQuestions; questionIndex++)
                    {
                        if (results[playerIndex, questionIndex] == 1)
                        {
                            values.Add(ranks[questionIndex]);
                        }
                    }

                    playersMeanRanks[playerIndex] = standardDeviation(values);

                }

                Console.WriteLine("Case #"+(Ti + 1)+": " + (maxValueIndex(playersMeanRanks) + 1));

            }

        }

        static double standardDeviation(IEnumerable<int> sequence)
        {
            double result = 0;

            if (sequence.Any())
            {
                double average = sequence.Average();
                double sum = sequence.Sum(d => Math.Pow(d - average, 2));
                result = Math.Sqrt((sum) / (sequence.Count() - 1));
            }
            return result;
        }

        static int maxValueIndex(double[] sequence) {
            int index = 0;
            for (int i = 0; i < sequence.Length; i++)
            {
                if (sequence[index] < sequence[i])
                {
                    index = i;
                }
            }
            return index;
        }

    }

    public class Question : IComparable<Question>
    {
        public int ID { set; get; }
        public int count { set; get; }

        public Question()
        {
            count = 0;
        }

        public int CompareTo(Question other)
        {
            return this.count.CompareTo(other.count);
        }
    }
}
